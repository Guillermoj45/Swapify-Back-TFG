package com.example.swapify_back.entities

import com.example.swapify_back.entities.enums.Rol
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "users") // Convención de nombres en SQL
class User: UserDetails, Serializable {

    @Id
    @Column(name = "id", nullable = false)
    val id: UUID = UUID.randomUUID()

    @Column(name = "email", nullable = false, unique = true)
    var email: String = ""

    @Column(name = "password", nullable = false)
    var passworde: String = ""

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "rol", nullable = false)
    var rol: Rol = Rol.USER

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    var profile: Profile? = null

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return listOf(SimpleGrantedAuthority(rol.name))
    }

    override fun getPassword(): String = passworde

    override fun getUsername(): String = email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
