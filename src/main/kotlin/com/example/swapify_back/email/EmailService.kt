package com.example.swapify_back.email

import com.example.swapify_back.service.UserService
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val userService: UserService,
    private val sendEmailClass: SendEmailClass
) {
    @Async
    fun sendWelcomeEmail(email: String?, name: String, verificationLink: String) {
        if (email.isNullOrBlank() || userService.exists(email)) {
            throw IllegalArgumentException("El correo no es válido o el usuario ya existe.")
        }

        val variables = mapOf(
            "name" to name,
            "LINK_VERIFICACION_EMAIL" to verificationLink
        )

        sendEmailClass.sendEmailWithTemplate(email, "Bienvenid@ a PowerZone", "index", variables)
    }
}
