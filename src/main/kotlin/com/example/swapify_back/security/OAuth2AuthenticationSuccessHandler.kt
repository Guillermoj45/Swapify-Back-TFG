package com.example.swapify_back.security

import com.example.swapify_back.config.JwtService
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class OAuth2AuthenticationSuccessHandler(
    private val jwtService: JwtService
) : SimpleUrlAuthenticationSuccessHandler() {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val customUserPrincipal = authentication.principal as CustomUserPrincipal
        val user = customUserPrincipal.user // Necesitarás agregar este método en CustomUserPrincipal

        // Generar token JWT
        val token = jwtService.generateToken(user)

        // Configurar la respuesta
        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"

        val responseBody = mapOf(
            "token" to token,
        //    "email" to user.email,
        //    "nickname" to user.profile?.nickname // Asumiendo que tienes acceso al perfil
        )

        ObjectMapper().writeValue(response.writer, responseBody)
    }
}