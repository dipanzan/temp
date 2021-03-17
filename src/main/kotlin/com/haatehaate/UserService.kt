package com.haatehaate

import com.haatehaate.entity.User
import com.haatehaate.registration.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun registerUser(user: User): Optional<User> {
        return Optional.of(userRepository.save(user))
    }

    fun existsUserByPhone(phone: String): Boolean {
        return userRepository.existsUserByPhone(phone)
    }

    fun findUserByPhoneAndPassword(phone: String, password: String): Optional<User> {
        return userRepository.findUserByPhoneAndPassword(phone, password)
    }
}