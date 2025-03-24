package com.example.swapify_back.service

import com.example.swapify_back.Entities.MessagesIA
import com.example.swapify_back.Repository.IConversIARepository

class ConversIAService(
    private val conversIARepository: IConversIARepository,
    private val messagesIA: MessagesIA
    ) {
}