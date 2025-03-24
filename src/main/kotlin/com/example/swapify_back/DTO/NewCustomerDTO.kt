package com.example.swapify_back.DTO

import com.example.swapify_back.entities.enums.Rol
import java.time.LocalDate
import java.util.UUID

data class NewCustomerDTO(
    val nickname: String,
    val email: String,
    val rol: Rol,
    val bornDate: LocalDate,
    val avatar: String,
    val password: String
)