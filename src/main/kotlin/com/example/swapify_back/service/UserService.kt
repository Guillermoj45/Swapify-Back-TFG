package com.example.swapify_back.service

import com.example.swapify_back.Entities.User
import com.example.swapify_back.Repository.IUserRepository
import org.springframework.stereotype.Service
import org.springframework.cache.annotation.Cacheable;
import java.util.*


@Service
class UserService(
    private val userRepository: IUserRepository
) {

    fun saveUser(user: User) : User {
        return userRepository.save(user)
    }

    @Cacheable(cacheNames = ["User"], key = "#id")
    fun findById(id: UUID): User {
        return userRepository.findById(id).get()
    }
}