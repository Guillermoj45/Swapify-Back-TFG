package com.example.swapify_back.controller

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController {

    @GetMapping("/")
    fun home(): String {
        return "¡Bienvenido! <a href=\"/oauth2/authorization/discord\">Iniciar sesión con Discord</a>"
    }

    @GetMapping("/welcome")
    fun welcome(@AuthenticationPrincipal oauth2User: OAuth2User): String {
        return oauth2User.attributes.toString()
    }
}