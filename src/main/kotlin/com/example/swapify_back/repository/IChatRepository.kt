package com.example.swapify_back.repository

import com.example.swapify_back.entities.Chat
import com.example.swapify_back.entities.Emdeabble.ChatId
import org.springframework.data.jpa.repository.JpaRepository

interface IChatRepository: JpaRepository<Chat, ChatId>{
}