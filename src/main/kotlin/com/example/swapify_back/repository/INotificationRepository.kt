package com.example.swapify_back.repository

import com.example.swapify_back.entities.Notification
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface INotificationRepository: JpaRepository<Notification, UUID> {
}