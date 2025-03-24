package com.example.swapify_back.service

import com.example.swapify_back.Repository.IChatRepository
import org.springframework.stereotype.Service

@Service
class ChatService(private val chatRepository: IChatRepository) {
}