package com.example.swapify_back.Repository

import com.example.swapify_back.Entities.Notification
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface INotificationRepository: JpaRepository<Notification, UUID> {
}