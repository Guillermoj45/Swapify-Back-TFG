package com.example.swapify_back.services

import com.example.swapify_back.DTO.NewCustomerDTO
import com.example.swapify_back.entities.Profile
import com.example.swapify_back.entities.enums.Rol
import com.example.swapify_back.repository.IUserRepository
import com.example.swapify_back.service.UserService
import jakarta.transaction.Transactional
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate

import org.junit.jupiter.api.Assertions.*

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class UserServiceTest() {

    @Autowired
    private val userService: UserService? = null

    @Autowired
    private val userRepository: IUserRepository? = null


    @Test
    @Tag("CrearUsuario")
    @DisplayName("Test para crear un nuevo usuario")
    fun testCrearUsuarioPositivo(){
        var newCostumer = NewCustomerDTO(
            nickname = "guillermo",
            email = "gremesalgarcia@safareyes.es",
            rol = Rol.USER,
            bornDate = LocalDate.of(2004, 8, 15),
            avatar = null,
            password = "contraseñaSegura"
        )

        var perfilTest: Profile = userService!!.saveUser(newCostumer)

        assertNotNull(perfilTest)
        assertEquals("guillermo", perfilTest.nickname)
        assertNotNull(perfilTest.avatar)
    }


}