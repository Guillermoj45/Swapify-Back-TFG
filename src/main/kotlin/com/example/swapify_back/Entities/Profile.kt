package com.example.swapify_back.Entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@Entity(name = "Profile")
@AllArgsConstructor
@NoArgsConstructor
class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private val id: Long? = null

    @Column(name = "nickname", nullable = false, unique = true)
    private val nickname: String = ""

    @Column(name = "username", nullable = false, unique = true)
    private val username: String = ""



}