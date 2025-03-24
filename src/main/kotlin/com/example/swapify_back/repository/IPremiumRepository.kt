package com.example.swapify_back.repository

import com.example.swapify_back.entities.Premium
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface IPremiumRepository: JpaRepository<Premium, UUID> {
}