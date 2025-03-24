package com.example.swapify_back.service

import com.example.swapify_back.entities.User
import com.example.swapify_back.repository.IUserRepository
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


    // @CacheEvict(value = "productos", key = "#id")
    // @CachePut(value = "productos", key = "#id")
    @Cacheable(cacheNames = ["User"], key = "#id")
    fun findById(id: UUID): User {
        return userRepository.findById(id).get()
    }
}