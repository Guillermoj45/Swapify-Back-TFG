package com.example.swapify_back.email

import com.example.swapify_back.service.UserService
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/email")
class EmailController(
    private val userService: UserService,
    private val emailService: EmailService
) {
    @Async
    fun sendWelcomeEmail(email: String?) {
        if (userService.exists(email)) {
            emailService.sendWelcomeEmail(email)
            println("NEGROS DE MIERDAAAAAAAAAAAA")
        }
    }

}