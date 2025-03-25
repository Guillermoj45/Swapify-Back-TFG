package com.example.swapify_back.entities

import com.example.swapify_back.entities.emdeabble.ChatId
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDateTime

@Data
@Entity(name = "chat")
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ChatId::class)
class Chat {

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    @Id
    @ManyToOne
    @JoinColumn(name = "id_user1", referencedColumnName = "id")
    var user1: User? = null

    @Id
    @ManyToOne
    @JoinColumn(name = "id_user2", referencedColumnName = "id")
    var user2: User? = null

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    var product: Product? = null
}