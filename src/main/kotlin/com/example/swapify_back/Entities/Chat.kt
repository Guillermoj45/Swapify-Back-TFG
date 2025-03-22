package com.example.swapify_back.Entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDateTime
import java.util.UUID

@Data
@Entity(name = "chat")
@AllArgsConstructor
@NoArgsConstructor
class Chat {

    @Id
    @Column(name = "id_user1", nullable = false)
    private val idUser1: UUID? = null

    @Id
    @Column(name = "id_user2", nullable = false)
    private val idUser2: UUID? = null

    @Id
    @Column(name = "id_product", nullable = false)
    private val idProduct: UUID? = null

    @Column(name = "created_at", nullable = false)
    private val createdAt: LocalDateTime = LocalDateTime.now()

    @ManyToOne
    @JoinColumn(name = "id_user1", referencedColumnName = "id", insertable = false, updatable = false)
    private val user1: User? = null

    @ManyToOne
    @JoinColumn(name = "id_user2", referencedColumnName = "id", insertable = false, updatable = false)
    private val user2: User? = null

    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id", insertable = false, updatable = false)
    private val product: Product? = null
}