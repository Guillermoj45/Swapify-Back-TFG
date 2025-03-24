package com.example.swapify_back.Entities

import jakarta.persistence.*
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
    var productId: UUID? = null


    @Column(name = "category", nullable = false)
    var category: String = ""

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    var product: Product? = null
}