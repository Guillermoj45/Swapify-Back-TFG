package com.example.swapify_back.service

import com.example.swapify_back.entities.User
import com.example.swapify_back.repository.IUserRepository
import com.example.swapify_back.Entities.User
import com.example.swapify_back.Repository.IUserRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
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

    @Throws(UsernameNotFoundException::class)
    fun loadUserByUsername(username: String?): User {
        if (username != null) {
            return userRepository.findByEmail(username)
        }
       return throw UsernameNotFoundException("User not found")
    }
}