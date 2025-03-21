package com.example.swapify_back.Entities

import com.example.swapify_back.Entities.Emdeabble.ChatId
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
    var id: UUID? = null

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    @ManyToOne
    @JoinColumn(name = "id_user1", referencedColumnName = "id")
    var user1: User? = null

    @ManyToOne
    @JoinColumn(name = "id_user2", referencedColumnName = "id")
    var user2: User? = null

    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id", insertable = false, updatable = false)
    var product: Product? = null
}