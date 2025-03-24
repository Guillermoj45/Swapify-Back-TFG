package com.example.swapify_back.config

import com.example.swapify_back.Config.JwtService
import com.example.swapify_back.DTO.TokenDTO
import com.example.swapify_back.Entities.User
import com.example.swapify_back.Services.UserService
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class JwtFilter(
    private val jwtService: JwtService,
    private val usuarioService: UserService
) : OncePerRequestFilter() { // Extend OncePerRequestFilter

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")

        if (request.servletPath.startsWith("/auth") || authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        val token = authHeader.substring(7).trim()
        val tokenDto: TokenDTO? = jwtService.extractTokenData(token)

        if (tokenDto != null && SecurityContextHolder.getContext().authentication == null) {
            val user: User? = usuarioService.loadUserByUsername(tokenDto.email)

            if (user != null && !jwtService.isExpired(token)) {
                val authToken = UsernamePasswordAuthenticationToken(
                    user.email, null, user.getAuthorities()
                )
                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authToken
            }
        }
        filterChain.doFilter(request, response)
    }
}