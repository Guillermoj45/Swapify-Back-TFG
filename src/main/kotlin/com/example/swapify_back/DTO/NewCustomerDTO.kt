package com.example.swapify_back.DTO

import com.example.swapify_back.entities.enums.Rol
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate
import java.util.UUID

data class NewCustomerDTO(
    val nickname: String,
    val email: String,
    val rol: Rol,
    val bornDate: LocalDate,
    val avatar: MultipartFile,
    val password: String
)