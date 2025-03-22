package com.example.swapify_back.Repository

import com.example.swapify_back.Entities.Chat
import com.example.swapify_back.Entities.Emdeabble.ChatId
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface IChatRepository: JpaRepository<Chat, ChatId>{
}