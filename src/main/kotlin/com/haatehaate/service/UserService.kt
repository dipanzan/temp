package com.haatehaate.service

import com.haatehaate.entity.User
import com.haatehaate.exception.InvalidLoginException
import com.haatehaate.exception.InvalidRegistrationException
import com.haatehaate.login.dto.LoginRequest
import com.haatehaate.token.TokenService
import com.haatehaate.repository.UserRepository
import com.haatehaate.utils.validator.Messages
import com.haatehaate.utils.validator.logger
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserService(
    private val userRepository: UserRepository,
    private val tokenService: TokenService
) {
    companion object {
        private val log = logger()
    }

    fun checkIfUserIsAlreadyRegistered(username: String) {
        if (userRepository.existsUserByUsernameAndOtpVerifiedIsTrue(username)) {
            throw InvalidRegistrationException(
                reason = "Username $username is already registered. Please login with credentials.",
                path = "/user/login"
            )
        }
    }

    fun registerUserWithOtpPending(username: String, password: String) {
        val unverifiedUser = User(username = username, password = password, otpVerified = false)
        val user = userRepository.findUserByUsername(username).orElse(unverifiedUser)
        user.username = username
        user.password = password
        userRepository.save(user)
    }

    fun checkIfUserHasCompletedOtpRequest(username: String) {
        userRepository.findUserByUsernameAndOtpVerifiedIsFalse(username).orElseThrow {
            InvalidRegistrationException(
                reason = "Username $username did not complete OTP request.",
                path = "/user/registration/new"
            )
        }
    }

    fun registerUserWithOtpVerified(username: String): User {
        val user = userRepository.findUserByUsernameAndOtpVerifiedIsFalse(username).orElseThrow {
            InvalidRegistrationException(
                reason = "Could not register username $username, please try again later.",
                path = "/user/registration/new"
            )
        }
        return userRepository.save(setTokenOtpRegisteredAtFor(user))
    }

    private fun setTokenOtpRegisteredAtFor(user: User): User {
        val token = tokenService.generateNewToken(user)
        return user
            .setToken(token)
            .setOtpVerified(true)
            .setRegisteredAt(LocalDateTime.now())
    }

    fun checkIfUserIsOtpVerified(username: String) {
        if (!userRepository.existsUserByUsernameAndOtpVerifiedIsTrue(username)) {
            throw InvalidLoginException(
                reason = "Username $username does not exist, please register an account.",
                path = "/user/registration/new"
            )
        }
    }

    fun authenticateUser(loginRequest: LoginRequest): User {
        val user = userRepository.findUserByUsernameAndPassword(
            username = loginRequest.username,
            password = loginRequest.password
        )
            .orElseThrow {
                InvalidLoginException(reason = Messages.INCORRECT_USERNAME_OR_PASSWORD, path = "/user/login")
            }
        tokenService.refreshUserToken(user)
        return user
    }
}

