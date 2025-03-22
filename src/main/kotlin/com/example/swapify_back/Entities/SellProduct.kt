package com.example.swapify_back.Entities

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDateTime
import java.util.UUID

@Data
@Entity(name = "sell_product")
@AllArgsConstructor
@NoArgsConstructor
class SellProduct {

    @Id
    @Column(name = "product_id", nullable = false)
    private val productId: UUID? = null

    @Column(name = "created_at", nullable = false)
    private val createdAt: LocalDateTime = LocalDateTime.now()

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private val user: User? = null

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private val product: Product? = null
}