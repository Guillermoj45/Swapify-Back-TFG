package com.example.swapify_back.Entities.Emdeabble

import jakarta.persistence.Embeddable
import java.io.Serializable
import java.util.*


class ChatId(): Serializable {
    var user1: UUID? = null
    var user2: UUID? = null
    var product: UUID? = null

    constructor(user1: UUID, user2: UUID, product: UUID): this() {
        this.user1 = user1
        this.user2 = user2
        this.product = product
    }
}