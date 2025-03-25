package com.example.swapify_back.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig(
    private val jwtFilterChain: JwtFilter,
    private val authenticationProvider: AuthenticationProvider,

) {
    @Value("\${SECURITY_REDIRECT_URL}")
    private val redirectUrl: String? = null

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity // Deshabilitar CSRF y CORS (si es necesario)
            .csrf { obj: CsrfConfigurer<HttpSecurity> -> obj.disable() }
            .cors { obj: CorsConfigurer<HttpSecurity> -> obj.disable() } // Configurar autenticación y filtros

            .authorizeHttpRequests(Customizer { req: AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry ->
                req
                    .requestMatchers("**")
                    .permitAll() //.requestMatchers("/auth/**", "/api/auth/forgot-password", "/api/auth/reset-password").permitAll()
                    //.requestMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            }
            )
            .oauth2Login { oauth2 ->
                oauth2.defaultSuccessUrl(redirectUrl, true)
            }
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtFilterChain, UsernamePasswordAuthenticationFilter::class.java)

        return httpSecurity.build()
    }
}