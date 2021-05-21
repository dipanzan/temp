package com.haatehaate.service

import com.haatehaate.entity.User
import com.haatehaate.exception.InvalidLoginException
import com.haatehaate.login.dto.LoginRequest
import com.haatehaate.repository.UserRepository
import com.haatehaate.utils.validator.Messages
import com.haatehaate.utils.validator.logger
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    companion object {
        private val log = logger()
    }

    fun loginUser(loginRequest: LoginRequest): User {
        checkIfUserDoesNotExist(loginRequest.username)

        return userRepository.findUserByUsernameAndPassword(
            loginRequest.username,
            loginRequest.password
        ).orElseThrow {
            InvalidLoginException(Messages.INCORRECT_USERNAME_OR_PASSWORD)
        }
    }

    private fun checkIfUserDoesNotExist(username: String) {
        if (!userRepository.existsUserByUsername(username)) {
            throw InvalidLoginException("Username $username does not exist, please register an account.")
        }
    }
}