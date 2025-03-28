package com.example.swapify_back.controller

import com.example.swapify_back.DTO.LoginDTO
import com.example.swapify_back.DTO.NewCustomerDTO
import com.example.swapify_back.DTO.RespuestaTokenDTO
import com.example.swapify_back.entities.Profile
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
    @PostMapping("/create")
    fun saveUser(@RequestBody user: NewCustomerDTO): ResponseEntity<Profile> {
        val savedUser = userService.saveUser(user)
        return ResponseEntity.ok(savedUser)
    }

    @GetMapping("/login")
    fun login(@RequestBody userLogin: LoginDTO): ResponseEntity<RespuestaTokenDTO> {
        val token = userService.loginUser(userLogin)
        return ResponseEntity.ok(token)
    }

    @GetMapping
    fun finUser(@RequestParam(value = "id", defaultValue = "0") id: UUID): User {
        return userService.findById(id)
    }

    @GetMapping("/verify/{token}")
    fun verifyNewUser(@PathVariable token: String): ResponseEntity<Void> {
        userService.verifyUser(token)
        return ResponseEntity(HttpStatus.OK)
    }
}