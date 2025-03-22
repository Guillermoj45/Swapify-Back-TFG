package com.example.swapify_back.Entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDateTime
import java.util.UUID

@Data
@Entity(name = "premiun")
@AllArgsConstructor
@NoArgsConstructor
class Premium {

    @Id
    @Column(name = "user_id", nullable = false)
    private val userId: UUID? = null

    @Column(name = "created_at", nullable = false)
    private val createdAt: LocalDateTime? = null

    @Column(name = "rol", nullable = false)
    private val rol: Int = 0
}