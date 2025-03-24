package com.example.swapify_back.entities

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

@Data
@Entity(name = "product")
@AllArgsConstructor
@NoArgsConstructor
class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    var id: UUID = UUID.randomUUID()

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
    var isActive: Boolean = true

    @ElementCollection
    @CollectionTable(name = "product_image", joinColumns = [JoinColumn(name = "product_id")])
    @Cascade(CascadeType.REMOVE)
    @Column(name = "image")
    var imagenes: MutableList<String> = ArrayList()

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    var profile: Profile? = null

    @OneToOne
    @JoinColumn(name = "id")
    var sellProduct: SellProduct? = null
}