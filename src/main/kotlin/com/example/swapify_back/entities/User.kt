package com.example.swapify_back.entities

import com.example.swapify_back.entities.enums.Rol
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

@Data
@Entity(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
class User: Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    var id:UUID = UUID.randomUUID()

    @Column(name = "email", nullable = false, unique = true)
    var email:String = ""

    @Column(name = "password", nullable = false)
    var password:String = ""

    @Column(name = "created_at", nullable = false)
    var createAt: LocalDateTime = LocalDateTime.now()
    
    @Column(name = "rol", nullable = false)
    var rol: Rol = Rol.USER

    fun getAuthorities(): Collection<GrantedAuthority?> {
        return listOf<SimpleGrantedAuthority>(SimpleGrantedAuthority(this.rol.name))
    }

    fun getUsername(): String {
        return this.email
    }

    fun isAccountNonExpired(): Boolean {
        return true
    }

    fun isAccountNonLocked(): Boolean {
        return true
    }

    fun isCredentialsNonExpired(): Boolean {
        return true
    }

    fun isEnabled(): Boolean {
        return true
    }

}