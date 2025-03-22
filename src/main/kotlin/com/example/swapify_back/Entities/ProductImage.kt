package com.example.swapify_back.Entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.util.UUID

@Data
@Entity(name = "product_image")
@AllArgsConstructor
@NoArgsConstructor
class ProductImage {

    @Id
    @Column(name = "product_id", nullable = false)
    private val productId: UUID? = null

    @Column(name = "image", nullable = false)
    private val image: String = ""

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private val product: Product? = null
}