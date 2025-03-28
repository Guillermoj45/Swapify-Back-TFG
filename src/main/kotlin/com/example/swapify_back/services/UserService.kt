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
    private val cloudinary: CloudinaryService,
    @Lazy private val emailController: EmailController
) {

    @Value("\${LINK_VERIFICACION_EMAIL}")
    private lateinit var verificationBaseUrl: String

    private val passwordEncoder = BCryptPasswordEncoder()

    @Transactional
    fun saveUser(userDto: NewCustomerDTO): Profile {
        val user = userRepository.save(
            User().apply {
                email = userDto.email
                passworde = passwordEncoder.encode(userDto.password)
            }
        )

        val verificationToken = generateVerificationToken(user.email)
        val verificationLink = "$verificationBaseUrl/$verificationToken"
        emailController.sendWelcomeEmail(user.email, userDto.nickname, verificationLink)

        val avatarPublicId = userDto.avatar?.let { uploadImageCloudinary(it).get().publicId }
            ?: "Swapify/avatares/vl4elwp26kqokvn2mk5g"

        return profileRepository.save(
            Profile().apply {
                id = user.id
                nickname = userDto.nickname
                bornDate = userDto.bornDate
                avatar = avatarPublicId
            }
        )
    }

    @Cacheable(cacheNames = ["User"], key = "#id")
    fun findById(id: UUID): User = userRepository.findById(id).orElseThrow { UsernameNotFoundException("User not found") }

    fun loadUserByUsername(username: String): User = userRepository.findByEmail(username).orElseThrow {
        UsernameNotFoundException("User not found")
    }

    fun loginUser(userLogin: LoginDTO): RespuestaTokenDTO {
        val user = userRepository.findByEmail(userLogin.email).orElseThrow {
            UsernameNotFoundException("User not found")
        }

        validateUser(user)
        validatePassword(userLogin.password, user.passworde)

        return RespuestaTokenDTO(
            estado = HttpStatus.OK.value(),
            token = jwtService.generateToken(user)
        )
    }

    private fun validateUser(user: User) {
        if (!user.isVerified) {
            throw IllegalArgumentException("User not verified")
        }
    }

    private fun validatePassword(password: String, hashedPassword: String) {
        if (!passwordEncoder.matches(password, hashedPassword)) {
            throw IllegalArgumentException("Invalid password")
        }
    }

    private fun uploadImageCloudinary(avatar: MultipartFile): CompletableFuture<UploadResult> =
        cloudinary.uploadFileAsync(avatar, null)

    fun exists(email: String): Boolean = userRepository.existsUserByEmail(email)

    fun generateVerificationToken(email: String): String = jwtService.generateToken(loadUserByUsername(email))

    fun verifyUser(token: String): Map<String, String> {
        val user = getUserFromToken(token)
        validateToken(token, user)
        return updateVerificationStatus(user)
    }

    private fun validateToken(token: String, user: User) {
        if (!jwtService.isTokenValid(token, user)) {
            throw IllegalArgumentException("Token inválido o expirado")
        }
    }

    private fun updateVerificationStatus(user: User): Map<String, String> {
        user.isVerified = true
        userRepository.save(user)
        return mapOf("message" to "Email verificado correctamente", "email" to user.email)
    }

    private fun getUserFromToken(token: String): User {
        val email = jwtService.extractUsername(token)
        return userRepository.findByEmail(email).orElseThrow {
            IllegalArgumentException("Usuario no encontrado")
        }
    }
}