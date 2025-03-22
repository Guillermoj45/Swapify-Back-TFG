package com.example.swapify_back.Entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDateTime
import java.util.UUID

@Data
@Entity(name = "messages_IA")
@AllArgsConstructor
@NoArgsConstructor
class MessagesIA {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private val id: UUID? = null

    @Column(name = "conversia_id", nullable = false)
    private val conversiaId: UUID? = null

    @Column(name = "message", nullable = false)
    private val message: String = ""

    @Column(name = "created_at", nullable = false)
    private val createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "is_user", nullable = false)
    private val isUser: Boolean = false

    @Column(name = "image")
    private val image: String? = null

    @ManyToOne
    @JoinColumn(name = "conversia_id", referencedColumnName = "id", insertable = false, updatable = false)
    private val conversIA: ConversIA? = null
}