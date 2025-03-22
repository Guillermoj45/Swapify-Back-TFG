package com.example.swapify_back.Entities

import com.example.swapify_back.Entities.Emdeabble.ChatId
import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
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
    @EmbeddedId
    var chatId: ChatId? = null

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    @ManyToOne
    @JoinColumn(name = "id_user1", referencedColumnName = "id", insertable = false, updatable = false)
    var user1: User? = null

    @ManyToOne
    @JoinColumn(name = "id_user2", referencedColumnName = "id", insertable = false, updatable = false)
    var user2: User? = null

    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id", insertable = false, updatable = false)
    var product: Product? = null
}