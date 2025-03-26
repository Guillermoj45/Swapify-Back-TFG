package com.example.swapify_back.email

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/email")
class EmailController(private val emailService: EmailService) {

    @PostMapping("/send-welcome")
    fun sendWelcomeEmail(@RequestParam email: String, @RequestParam name: String): String {
        return try {
            emailService.sendWelcomeEmail(email, name)
            "Correo de bienvenida enviado a $email"
        } catch (e: Exception) {
            "Error al enviar el correo: ${e.message}"
        }
    }
}
