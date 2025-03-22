package com.example.swapify_back.Entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDateTime
import java.util.UUID

@Data
@Entity(name = "product")
@AllArgsConstructor
@NoArgsConstructor
class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private val id: UUID? = null

    @Column(name = "user_id", nullable = false)
    private val userId: UUID? = null

    @Column(name = "name", nullable = false)
    private val name: String = ""

    @Column(name = "description", nullable = false)
    private val description: String = ""

    @Column(name = "points", nullable = false)
    private val points: Int = 0

    @Column(name = "created_at", nullable = false)
    private val createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "updated_at", nullable = false)
    private val updatedAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "is_active")
    private val isActive: Boolean? = null

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private val user: User? = null
}