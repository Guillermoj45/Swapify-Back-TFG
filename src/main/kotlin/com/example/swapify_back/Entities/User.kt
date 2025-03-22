package com.example.swapify_back.Entities

import com.example.swapify_back.Entities.enums.Rol
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.Cascade
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
    private val id:UUID? = null

    @Column(name = "email", nullable = false, unique = true)
    private val email:String = ""

    @Column(name = "password", nullable = false)
    private val password:String = ""

    @Column(name = "created_at", nullable = false)
    private val createAt: LocalDateTime? = null
}