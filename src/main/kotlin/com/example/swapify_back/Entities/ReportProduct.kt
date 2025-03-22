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
@Entity(name = "report_product")
@AllArgsConstructor
@NoArgsConstructor
class ReportProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private val id: UUID? = null

    @Column(name = "product_id", nullable = false)
    private val productId: UUID? = null

    @Column(name = "user_id", nullable = false)
    private val userId: UUID? = null

    @Column(name = "type", nullable = false)
    private val type: Short = 0

    @Column(name = "description", nullable = false)
    private val description: String = ""

    @Column(name = "created_at", nullable = false)
    private val createdAt: LocalDateTime = LocalDateTime.now()

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private val product: Product? = null

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private val user: User? = null
}