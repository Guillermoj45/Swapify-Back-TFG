package com.example.swapify_back.controller

import com.example.swapify_back.Entities.User
import com.example.swapify_back.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    private var userService: UserService
) {
    @PostMapping
    fun saveUser(@RequestBody user: User):User {
        return userService.saveUser(user)
    }

}