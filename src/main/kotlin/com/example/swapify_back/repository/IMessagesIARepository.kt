package com.example.swapify_back.repository

import com.example.swapify_back.entities.MessagesIA
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface IMessagesIARepository: JpaRepository<MessagesIA, UUID> {
}