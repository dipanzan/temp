package com.haatehaate.registration.service

import com.haatehaate.entity.User
import com.haatehaate.registration.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun existsUserByPhone(phone: String): Boolean {
        return userRepository.existsUserByPhone(phone)
    }

    fun registerUser(user: User) {
        userRepository.save(user)
    }


}