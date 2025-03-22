package com.example.swapify_back.Entities

import com.example.swapify_back.Entities.enums.Rol
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDateTime
import java.util.UUID

@Data
@Entity(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    var id:UUID? = null

    @Column(name = "email", nullable = false, unique = true)
    var email:String = ""

    @Column(name = "password", nullable = false)
    var password:String = ""

    @Column(name = "created_at", nullable = false)
    var createAt: LocalDateTime? = null
    
    @Column(name = "rol", nullable = false)
    var rol: Rol = Rol.USER
}