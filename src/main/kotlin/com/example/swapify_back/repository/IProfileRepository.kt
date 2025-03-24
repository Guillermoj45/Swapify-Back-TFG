package com.example.swapify_back.repository

import com.example.swapify_back.entities.Profile
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface IProfileRepository: JpaRepository<Profile, UUID> {
}