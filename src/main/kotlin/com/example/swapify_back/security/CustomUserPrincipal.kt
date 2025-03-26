package com.example.swapify_back.security

import com.example.swapify_back.entities.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User

data class CustomUserPrincipal(
    val user: User,
    private val attributes: Map<String, Any>,
) : OAuth2User {
    override fun getAttributes(): Map<String, Any> = attributes

    override fun getAuthorities(): Collection<GrantedAuthority> =
        listOf(SimpleGrantedAuthority(user.rol.name))

    override fun getName(): String = user.email
}