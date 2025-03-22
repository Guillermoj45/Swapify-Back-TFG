package com.example.swapify_back.Entities.Emdeabble

import jakarta.persistence.Embeddable
import java.io.Serializable
import java.util.*

@Embeddable
data class ChatId(
    val id_user1: UUID? = null,
    val id_user2: UUID? = null,
    val id_product: UUID? = null
) : Serializable