package com.example.swapify_back.Entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

@Data
@Entity(name = "Profile")
@AllArgsConstructor
@NoArgsConstructor
class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private val id: UUID? = null

    @Column(name = "nickname", nullable = false, unique = true)
    private val nickname: String = ""

    @Column(name = "avatar", nullable = false)
    private val avatar: String = ""

    @Column(name = "born_date")
    private val bornDate: LocalDate? = null

    @Column(name = "ban_at")
    private val banAt: LocalDate? = null

    @Column(name = "is_new_user", nullable = false)
    private val isNewUser: Boolean = true

    @Column(name = "create_at", nullable = false)
    private val createAt: LocalDateTime = LocalDateTime.now()

    @OneToOne
    @JoinColumn(name = "id")
    val user: User? = null

}