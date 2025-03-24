package com.example.swapify_back.service

import com.example.swapify_back.entities.MessagesIA
import com.example.swapify_back.repository.IConversIARepository

class ConversIAService(
    private val conversIARepository: IConversIARepository,
    private val messagesIA: MessagesIA
    ) {
}