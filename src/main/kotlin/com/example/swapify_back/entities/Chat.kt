package com.example.swapify_back.entities

import jakarta.persistence.*
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID = UUID.randomUUID()

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    @ManyToOne
    @JoinColumn(name = "id_user1", referencedColumnName = "id")
    var user1: User? = null

    @ManyToOne
    @JoinColumn(name = "id_user2", referencedColumnName = "id")
    var user2: User? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    var product: Product? = null
}