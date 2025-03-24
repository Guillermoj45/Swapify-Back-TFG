package com.example.swapify_back.entities

import com.example.swapify_back.entities.enums.Rol
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.io.Serializable
import java.time.LocalDateTime
import java.util.UUID

@Data
@Entity(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
class User: Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    var id:UUID? = null

    @Column(name = "email", nullable = false, unique = true)
    var email:String = ""

    @Column(name = "password", nullable = false)
    var password:String = ""

    @Column(name = "created_at", nullable = false)
    var createAt: LocalDateTime = LocalDateTime.now()
    
    @Column(name = "rol", nullable = false)
    var rol: Rol = Rol.USER

}