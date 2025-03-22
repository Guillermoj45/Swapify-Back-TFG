package com.example.swapify_back.Repository

import com.example.swapify_back.Entities.Profile
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface IProfileRepository: JpaRepository<Profile, UUID> {
}