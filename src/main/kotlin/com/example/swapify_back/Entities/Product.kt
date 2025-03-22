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
    private val id: UUID? = null

    @Column(name = "profile_id", nullable = false)
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

    @ElementCollection
    @CollectionTable(name = "product_image", joinColumns = [JoinColumn(name = "product_id")])
    @Cascade(CascadeType.REMOVE)
    @Column(name = "image")
    private var imagenes: List<String>? = null

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private val user: User? = null

    @OneToOne
    @JoinColumn(name = "id")
    private val sellProduct: SellProduct? = null
}