package com.example.swapify_back.repository

import com.example.swapify_back.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface IUserRepository: JpaRepository<User, UUID> {
    fun findByEmail(email: String): Optional<User>
}