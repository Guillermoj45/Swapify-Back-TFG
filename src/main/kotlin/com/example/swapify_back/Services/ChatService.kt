package com.example.swapify_back.Services

import com.example.swapify_back.repository.IChatRepository
import com.example.swapify_back.entities.Chat
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Service

@Service
class ChatService(private val chatRepository: IChatRepository) {

}