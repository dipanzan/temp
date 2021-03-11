package com.haatehaate.registration.service

import com.haatehaate.entity.User
import com.haatehaate.registration.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class RegisrationService(
    private val userRepository: UserRepository
) {
    fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }


}