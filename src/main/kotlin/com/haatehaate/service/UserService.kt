package com.haatehaate.service

import com.haatehaate.entity.User
import com.haatehaate.login.LoginRequest
import com.haatehaate.exception.InvalidLoginException
import com.haatehaate.exception.InvalidRegistrationException
import com.haatehaate.registration.dto.RegistrationRequest
import com.haatehaate.repository.UserRepository
import com.haatehaate.utils.validator.RegistrationValidator
import com.haatehaate.utils.validator.Validation
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val registrationValidator: RegistrationValidator
) {
    companion object {
        private const val INCORRECT_USERNAME_OR_PASSWORD = "Incorrect username or password."
    }

    fun loginUser(loginRequest: LoginRequest): User {
        checkIfUserDoesNotExist(loginRequest.username)

        val user = userRepository.findUserByUsernameAndPassword(
            loginRequest.username,
            loginRequest.password
        )
        user.orElseThrow {
            InvalidLoginException(INCORRECT_USERNAME_OR_PASSWORD)
        }
        return user.get()
    }

    fun setupRegistrationForNewUser(registrationRequest: RegistrationRequest) {
        validateRegistrationRequest(registrationRequest)
        checkIfUserExists(registrationRequest.username)


    }

    private fun checkIfUserDoesNotExist(username: String)  {
        if (!userRepository.existsUserByUsername(username)) {
            throw InvalidLoginException("Username $username does not exist, please register an account.")
        }
    }

    private fun checkIfUserExists(username: String) {
        if (userRepository.existsUserByUsername(username)) {
            throw InvalidRegistrationException("Username $username already exists. Please login with credentials.")
        }
    }

    private fun validateRegistrationRequest(registrationRequest: RegistrationRequest) {
        val validatedRegistration = registrationValidator.validateRegistrationRequest(registrationRequest)

        registrationValidator.validateRegistrationRequest(registrationRequest).forEach {
            if (it.value is Validation.Error) {
                throw InvalidRegistrationException(validatedRegistration)
            }
        }
    }
}