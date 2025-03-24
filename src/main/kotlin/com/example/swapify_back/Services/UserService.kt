package com.example.swapify_back.service

import com.example.swapify_back.DTO.NewCustomerDTO
import com.example.swapify_back.entities.Profile
import com.example.swapify_back.entities.User
import com.example.swapify_back.repository.IProfileRepository
import com.example.swapify_back.repository.IUserRepository
import jakarta.transaction.Transactional
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.cache.annotation.Cacheable;
import java.util.*


@Service
class UserService(
    private val userRepository: IUserRepository,
    private val profileRepository: IProfileRepository
) {

    @Transactional
    fun saveUser(userdto: NewCustomerDTO) {
        var user1: User = User()
        user1.email = userdto.email
        user1.passworde = userdto.password

        user1 = userRepository.save(user1)

        val profile1: Profile = Profile()
        profile1.id = user1.id
        profile1.nickname = userdto.nickname
        profile1.avatar = userdto.avatar
        profile1.bornDate = userdto.bornDate
        profile1.user = user1

        profileRepository.save(profile1)
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
            return userRepository.findByEmail(username).orElseThrow() {
                UsernameNotFoundException("User not found")
            }
        }
       return throw UsernameNotFoundException("User not found")
    }
}