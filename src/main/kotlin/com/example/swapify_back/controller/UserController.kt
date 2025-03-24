package com.example.swapify_back.controller

import com.example.swapify_back.DTO.NewCustomerDTO
import com.example.swapify_back.entities.User
import com.example.swapify_back.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/user")
class UserController(
    private var userService: UserService
) {
    @PostMapping
    fun saveUser(@RequestBody user: NewCustomerDTO): ResponseEntity<Void> {
        userService.saveUser(user)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @GetMapping
    fun finUser(@RequestParam(value = "id", defaultValue = "0") id: UUID): User {
        return userService.findById(id)
    }
}