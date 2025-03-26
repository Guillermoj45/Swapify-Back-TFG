package com.example.swapify_back.service

import com.example.swapify_back.DTO.LoginDTO
import com.example.swapify_back.DTO.NewCustomerDTO
import com.example.swapify_back.DTO.RespuestaTokenDTO
import com.example.swapify_back.cloudinary.CloudinaryService
import com.example.swapify_back.cloudinary.UploadResult
import com.example.swapify_back.config.JwtService
import com.example.swapify_back.email.EmailController
import com.example.swapify_back.entities.Profile
import com.example.swapify_back.entities.User
import com.example.swapify_back.repository.IProfileRepository
import com.example.swapify_back.repository.IUserRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.Cacheable
import org.springframework.context.annotation.Lazy
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*
import java.util.concurrent.CompletableFuture


@Service
class UserService(
    private val userRepository: IUserRepository,
    private val profileRepository: IProfileRepository,
    private val jwtService: JwtService,
    private val clodinary: CloudinaryService,
    @Lazy private val email: EmailController
) {

    @Value("\${LINK_VERIFICACION_EMAIL}")
    private lateinit var verificationBaseUrl: String


    @Transactional
    fun saveUser(userdto: NewCustomerDTO): Profile {
        val passwordEncoder = BCryptPasswordEncoder()
        var user1 = User().apply {
            email = userdto.email
            passworde = passwordEncoder.encode(userdto.password)
        }
        user1 = userRepository.save(user1)

        val verificationToken = generateVerificationToken(user1.email)
        val verificationLink = "$verificationBaseUrl/$verificationToken"

        val profile1 = Profile().apply {
            id = user1.id
            nickname = userdto.nickname
            bornDate = userdto.bornDate
            avatar = if (userdto.avatar != null) {
                val futuroArchivo = subidaImagenCloudinary(avatar = userdto.avatar)
                futuroArchivo.get().publicId
            } else {
                "Swapify/avatares/vl4elwp26kqokvn2mk5g"
            }
        }

        email.sendWelcomeEmail(user1.email, userdto.nickname, verificationLink)

        return profileRepository.save(profile1)
    }

    // @CacheEvict(value = "productos", key = "#id")
    // @CachePut(value = "productos", key = "#id")
    @Cacheable(cacheNames = ["User"], key = "#id")
    fun findById(id: UUID): User {
        return userRepository.findById(id).get()
    }

    @Throws(UsernameNotFoundException::class)
    fun loadUserByUsername(username: String?): User {
        if (username != null) {
            return userRepository.findByEmail(username).orElseThrow() {
                UsernameNotFoundException("User not found")
            }
        }
       return throw UsernameNotFoundException("User not found")
    }

    fun loginUser(userLogin:LoginDTO ): RespuestaTokenDTO{
        var user: Optional<User> = userRepository.findByEmail(userLogin.email)
        if (!user.isPresent){
            return throw UsernameNotFoundException("User not found")
        }
        if (!user.get().isVerified){
            return throw IllegalArgumentException("User not verified")
        }

        isPasswordValid(userLogin.password, user.get().passworde)

        val generatedToken: String = jwtService.generateToken(user.get())

        return RespuestaTokenDTO(
            estado = HttpStatus.OK.value(),
            token = generatedToken
        )
    }

    private fun isPasswordValid(password: String, passworde: String) {
        if (!BCryptPasswordEncoder().matches(password, passworde)) {
            throw IllegalArgumentException("Invalid password")
        }
    }

    private fun subidaImagenCloudinary(avatar: MultipartFile): CompletableFuture<UploadResult> {
        return clodinary.uploadFileAsync(avatar, null)
    }

    fun exists(email: String): Boolean {
        val si: Boolean = userRepository.existsUserByEmail(email)
        return si
    }

    fun generateVerificationToken(email: String): String {
        return jwtService.generateToken(loadUserByUsername(email))
    }

    fun verifyUser(token: String): Map<String, String> {
        val email = jwtService.extractUsername(token)
        val user = userRepository.findByEmail(email)
            .orElseThrow { IllegalArgumentException("Usuario no encontrado") }

        if (!jwtService.isTokenValid(token, user)) {
            throw IllegalArgumentException("Token inválido o expirado")
        }

        user.isVerified = true
        userRepository.save(user)

        return mapOf(
            "message" to "Email verificado correctamente",
            "email" to user.email
        )
    }

}