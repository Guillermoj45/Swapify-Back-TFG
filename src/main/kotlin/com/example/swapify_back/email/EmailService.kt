package com.example.swapify_back.email

import com.example.swapify_back.service.UserService
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
open class EmailService(
    private val userService: UserService,
    private val sendEmailClass: SendEmailClass
) {

    @Async
    open fun sendWelcomeEmail(email: String?) {
        if (userService.exists(email)) {
            sendEmailClass.sendEmail(
                email,
                "Bienvenid@ a PowerZone",
                """
                🏋️‍♂️ ¡Bienvenido a PowerZone! 🍏

                ¡Nos emociona tenerte aquí! 🚀 PowerZone es la red social diseñada para los apasionados del fitness y la vida saludable. Aquí podrás:

                ✅ Compartir tu progreso con fotos y publicaciones.
                🤖 Preguntar a nuestra IA sobre entrenamientos, nutrición y bienestar.
                💬 Chatear con otros usuarios y hacer comunidad.
                🏆 Descubrir nuevos retos y mantenerte motivado.

                Estás en el lugar perfecto para alcanzar tus objetivos y conectar con personas que comparten tu estilo de vida. ¡Empieza explorando y haz que cada día cuente! 💪🔥

                📲 ¿Listo para la acción? Comparte tu primera publicación o únete a la conversación.

                #PowerZone #Fitness #Salud #Comunidad 💪💪
                """.trimIndent()
            )
        }
    }
}