package com.example.swapify_back

import com.example.swapify_back.entities.User
import com.example.swapify_back.service.UserService
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Lazy

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class SwapifyBackApplicationTests(

) {
    @Lazy
    @Autowired
    private val userService: UserService? = null

    @Test
    fun contextLoads() {
        assert(true)
    }
}