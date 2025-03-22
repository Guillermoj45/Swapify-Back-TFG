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
@Entity(name = "save_product")
@AllArgsConstructor
@NoArgsConstructor
class SaveProduct {

    @Id
    @Column(name = "user_id", nullable = false)
    var userId: UUID? = null

    @Id
    @Column(name = "product_id", nullable = false)
    var productId: UUID? = null

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    var user: User? = null

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    var product: Product? = null
}