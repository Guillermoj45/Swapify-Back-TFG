package com.example.swapify_back.services

import com.example.swapify_back.repository.IChatRepository
import org.springframework.stereotype.Service

@Service
class ChatService(private val chatRepository: IChatRepository) {

}