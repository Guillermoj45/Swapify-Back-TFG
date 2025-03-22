package com.example.swapify_back.Entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
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
    @GeneratedValue(strategy = GenerationType.UUID)
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

    @OneToOne
    @JoinColumn(name = "id")
    @JsonIgnore
    val user: User? = null

    @OneToOne
    @JoinColumn(name = "id")
    val settings: Settings? = null

    @OneToOne
    @JoinColumn(name = "id")
    val premium: Premium? = null

    @OneToMany
    @JoinColumn(name = "profile_id", referencedColumnName = "id", insertable = false, updatable = false)
    val products: List<Product>? = null
}