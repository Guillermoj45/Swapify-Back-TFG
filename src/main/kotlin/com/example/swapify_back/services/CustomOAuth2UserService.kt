package com.example.swapify_back.services

import com.example.swapify_back.DTO.NewCustomerDTO
import com.example.swapify_back.entities.User
import com.example.swapify_back.entities.enums.Rol
import com.example.swapify_back.exceptions.OAuth2AuthenticationProcessingException
import com.example.swapify_back.security.CustomUserPrincipal
import com.example.swapify_back.service.UserService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*
import org.springframework.web.multipart.MultipartFile
import java.net.URI


@Service
class CustomOAuth2UserService(
    private val userService: UserService
) : DefaultOAuth2UserService() {

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val oAuth2User = super.loadUser(userRequest)

        try {
            return processOAuth2User(userRequest, oAuth2User)
        } catch (ex: Exception) {
            throw OAuth2AuthenticationProcessingException("Failed to process OAuth2 user registration")
        }
    }

    private fun processOAuth2User(request: OAuth2UserRequest, oAuth2User: OAuth2User): OAuth2User {
        val email = oAuth2User.attributes["email"] as String


        // Intenta encontrar usuario existente
        val userOptional = try {
            Optional.of(userService.loadUserByUsername(email))
        } catch (e: UsernameNotFoundException) {
            Optional.empty()
        }

        val user: User = if (userOptional.isPresent) {
            userOptional.get()
        } else {
            registerNewUser(request, oAuth2User)
        }

        return CustomUserPrincipal(user, oAuth2User.attributes)
    }

    private fun registerNewUser(request: OAuth2UserRequest, oAuth2User: OAuth2User): User {
        val provider = request.clientRegistration.registrationId
        val avatarUrl = when (provider) {
            "google" -> oAuth2User.attributes["picture"] as String?
            "discord" -> "https://cdn.discordapp.com/avatars/${oAuth2User.attributes["id"]}/${oAuth2User.attributes["avatar"]}.png"
            else -> null
        }
        val nickname = when (provider) {
            "discord" -> oAuth2User.attributes["username"] as String
            "google" -> (oAuth2User.attributes["given_name"] as String? ?: "user") + UUID.randomUUID().toString().substring(0, 4)
            else -> "user_${UUID.randomUUID().toString().substring(0, 8)}"
        }

        val newUser = NewCustomerDTO(
            email = oAuth2User.attributes["email"] as String,
            password = UUID.randomUUID().toString(),
            nickname = nickname,
            bornDate = LocalDate.now(),
            avatar = avatarUrl?.let { url -> urlToMultipartFile(url) },
            rol = Rol.USER
        )

        userService.saveUser(newUser)
        return userService.loadUserByUsername(newUser.email)
    }

    private fun urlToMultipartFile(url: String): MultipartFile {
        val originalUrl = URI(url).toURL()
        val connection = originalUrl.openConnection()
        val input = connection.getInputStream()
        val fileName = url.substring(url.lastIndexOf('/') + 1)

        return object : MultipartFile {
            override fun getName(): String = fileName
            override fun getOriginalFilename(): String = fileName
            override fun getContentType(): String? = connection.contentType
            override fun isEmpty(): Boolean = input.available() == 0
            override fun getSize(): Long = input.available().toLong()
            override fun getBytes(): ByteArray = input.readBytes()
            override fun getInputStream(): java.io.InputStream = input
            override fun transferTo(dest: java.io.File) {
                dest.writeBytes(getBytes())
            }
        }
    }
}