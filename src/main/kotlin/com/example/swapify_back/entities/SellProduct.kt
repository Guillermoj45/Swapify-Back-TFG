package com.example.swapify_back.entities

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
    var productId: UUID? = null

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    var user: User? = null

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    var product: Product? = null
}