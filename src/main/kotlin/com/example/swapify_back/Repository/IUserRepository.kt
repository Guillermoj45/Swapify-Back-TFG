package com.example.swapify_back.Repository

import com.example.swapify_back.Entities.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface IUserRepository: JpaRepository<User, UUID> {
}