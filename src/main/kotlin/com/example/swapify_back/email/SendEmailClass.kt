package com.example.swapify_back.email

import jakarta.mail.MessagingException
import jakarta.mail.internet.MimeMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context

@Service
class SendEmailClass(
    private val mailSender: JavaMailSender,
    private val templateEngine: TemplateEngine
) {

    @Throws(MessagingException::class)
    fun sendEmailWithTemplate(to: String?, subject: String?, templateName: String, variables: Map<String, Any>) {
        if (to.isNullOrBlank() || subject.isNullOrBlank()) {
            throw IllegalArgumentException("El destinatario y asunto no pueden estar vacíos.")
        }

        // Crear mensaje de correo
        val message: MimeMessage = mailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true)

        // Procesar plantilla Thymeleaf con variables
        val context = Context()
        context.setVariables(variables)
        val htmlContent: String = templateEngine.process(templateName, context)

        // Configurar mensaje
        helper.setTo(to)
        helper.setSubject(subject)
        helper.setText(htmlContent, true)  // ✅ Habilitamos el HTML
        helper.setFrom("fmontesestrabon@safareyes.es")

        // Enviar correo
        mailSender.send(message)
    }
}
