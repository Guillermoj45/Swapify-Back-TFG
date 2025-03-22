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
@Entity(name = "product_category")
@AllArgsConstructor
@NoArgsConstructor
class ProductCategory {

    @Id
    @Column(name = "product_id", nullable = false)
    private val productId: UUID? = null

    @Column(name = "category", nullable = false)
    private val category: String = ""

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private val product: Product? = null
}