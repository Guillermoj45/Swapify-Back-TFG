package com.example.swapify_back.Entities.Emdeabble

import jakarta.persistence.Embeddable
import java.io.Serializable
import java.util.*

@Embeddable
data class ChatId(
    val idUser1: UUID? = null,
    val idUser2: UUID? = null,
    val idProduct: UUID? = null
) : Serializable