package com.example.swapify_back.entities

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
@Entity(name = "notification")
@AllArgsConstructor
@NoArgsConstructor
class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: UUID = UUID.randomUUID()

    @Column(name = "user_emisor_id", nullable = false)
    var userEmisorId: UUID? = null

    @Column(name = "user_receptor_id", nullable = false)
    var userReceptorId: UUID? = null

    @Column(name = "type", nullable = false)
    var type: Short = 0

    @Column(name = "content", nullable = false, columnDefinition = "jsonb")
    var content: String = ""

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "is_read", nullable = false)
    var isRead: Boolean = false

    @ManyToOne
    @JoinColumn(name = "user_emisor_id", referencedColumnName = "id", insertable = false, updatable = false)
    var userEmisor: User? = null

    @ManyToOne
    @JoinColumn(name = "user_receptor_id", referencedColumnName = "id", insertable = false, updatable = false)
    var userReceptor: User? = null
}