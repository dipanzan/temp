package com.haatehaate.registration

import com.haatehaate.entity.User
import org.springframework.stereotype.Service

@Service
class RegisrationService(
    private val userRepository: UserRepository
) {
    fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }


}