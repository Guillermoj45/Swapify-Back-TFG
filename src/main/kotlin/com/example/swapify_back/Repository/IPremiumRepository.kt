package com.example.swapify_back.Repository

import com.example.swapify_back.Entities.Premium
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface IPremiumRepository: JpaRepository<Premium, UUID> {
}