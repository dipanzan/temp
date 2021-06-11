package com.haatehaate.service

import com.haatehaate.entity.User
import com.haatehaate.login.dto.LoginRequest
import org.springframework.stereotype.Service

@Service
class LoginService(
    private val userService: UserService
) {
    fun loginUser(loginRequest: LoginRequest): User {
        userService.checkIfUserIsOtpVerified(loginRequest.username)
        return userService.authenticateUser(loginRequest)
    }

    fun logout() {

    }
}
