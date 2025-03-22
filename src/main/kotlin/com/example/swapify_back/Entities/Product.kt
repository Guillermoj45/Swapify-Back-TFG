package com.example.swapify_back.Entities

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import java.time.LocalDateTime
import java.util.*

@Data
@Entity(name = "product")
@AllArgsConstructor
@NoArgsConstructor
class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    var id: UUID? = null

    @Column(name = "profile_id", nullable = false)
    var userId: UUID? = null

    @Column(name = "name", nullable = false)
    var name: String = ""

    @Column(name = "description", nullable = false)
    var description: String = ""

    @Column(name = "points", nullable = false)
    var points: Int = 0

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "is_active")
    var isActive: Boolean? = null

    @ElementCollection
    @CollectionTable(name = "product_image", joinColumns = [JoinColumn(name = "product_id")])
    @Cascade(CascadeType.REMOVE)
    @Column(name = "image")
    var imagenes: List<String>? = null

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    var user: User? = null

    @OneToOne
    @JoinColumn(name = "id")
    var sellProduct: SellProduct? = null
}