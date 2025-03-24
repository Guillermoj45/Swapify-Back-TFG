package com.example.swapify_back.repository

import com.example.swapify_back.entities.Settings
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ISettingsRepository: JpaRepository<Settings, UUID> {
}