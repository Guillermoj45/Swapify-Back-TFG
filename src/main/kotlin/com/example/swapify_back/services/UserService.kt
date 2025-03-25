package com.example.swapify_back.service

import com.example.swapify_back.DTO.LoginDTO
import com.example.swapify_back.DTO.NewCustomerDTO
import com.example.swapify_back.DTO.RespuestaTokenDTO
import com.example.swapify_back.cloudinary.CloudinaryService
import com.example.swapify_back.cloudinary.UploadResult
import com.example.swapify_back.config.JwtService
import com.example.swapify_back.entities.Profile
import com.example.swapify_back.entities.User
import com.example.swapify_back.repository.IProfileRepository
import com.example.swapify_back.repository.IUserRepository
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.cache.annotation.Cacheable
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
    private val clodinary: CloudinaryService
) {

    @Transactional
    fun saveUser(userdto: NewCustomerDTO) {

        val passwordEncoder = BCryptPasswordEncoder()
        var user1 = User()
        user1.email = userdto.email
        user1.passworde = passwordEncoder.encode(userdto.password)
        user1 = userRepository.save(user1)

        val futuroArchivo: CompletableFuture<UploadResult> = subidaImagenCloudinary(avatar = userdto.avatar)
        val profile1 = Profile()
        profile1.id = user1.id
        profile1.nickname = userdto.nickname
        profile1.avatar = futuroArchivo.get().publicId
        profile1.bornDate = userdto.bornDate

        profileRepository.save(profile1)
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
}