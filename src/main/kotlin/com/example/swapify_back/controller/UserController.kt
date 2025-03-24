package com.example.swapify_back.controller

import com.example.swapify_back.entities.User
import com.example.swapify_back.Services.UserService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/user")
class UserController(
    private var userService: UserService
) {
    @PostMapping
    fun saveUser(@RequestBody user: User):User {
        return userService.saveUser(user)
    }

    @GetMapping
    fun finUser(@RequestParam(value = "id", defaultValue = "0") id: UUID): User {
        return userService.findById(id)
    }
}