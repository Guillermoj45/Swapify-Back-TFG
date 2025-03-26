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
        val newUser = NewCustomerDTO(
            email = oAuth2User.attributes["email"] as String,
            password = UUID.randomUUID().toString(),
            nickname = oAuth2User.attributes["name"] as String,
            bornDate = LocalDate.now(),
            avatar = null,
            rol = Rol.USER // Use enum instead of string
        )

        userService.saveUser(newUser)
        return userService.loadUserByUsername(newUser.email)
    }
}