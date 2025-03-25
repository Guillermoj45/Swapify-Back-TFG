package com.example.swapify_back.repository

import com.example.swapify_back.entities.Chat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface IChatRepository: JpaRepository<Chat, UUID>{
    @Query("""
        select *
        from chat
        where ((id_user1 = :userId and id_user2 = :emisorUser) or (id_user1 = :emisorUser and id_user2 = :userId)) and id_product
        order by created_at desc
    """, nativeQuery = true)
    fun getMessagesByChatUserId(userId: UUID, emisorUser:UUID, product:UUID): MutableList<Chat>
}