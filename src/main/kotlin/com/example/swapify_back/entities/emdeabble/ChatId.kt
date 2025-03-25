package com.example.swapify_back.entities.emdeabble

import java.io.Serializable
import java.util.*


class ChatId(
    var user1: UUID,
    var user2: UUID,
    var product: UUID
): Serializable