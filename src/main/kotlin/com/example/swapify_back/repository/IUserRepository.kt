package com.example.swapify_back.repository

import com.example.swapify_back.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface IUserRepository: JpaRepository<User, UUID> {
    fun findByEmail(email: String): User
}