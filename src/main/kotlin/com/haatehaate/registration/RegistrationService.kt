package com.haatehaate.registration

import com.haatehaate.entity.User
import com.haatehaate.exception.InvalidRegistrationException
import com.haatehaate.otp.OtpRequest
import com.haatehaate.otp.SmsService
import com.haatehaate.registration.dto.RegistrationRequest
import com.haatehaate.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class RegistrationService(
    private val userRepository: UserRepository,
    private val smsService: SmsService
) {

    fun completeUserRegistration(otpRequest: OtpRequest) {
        checkIfUserHasCompletedOtpRequest(otpRequest.username)
        validateOtp(otpRequest)
    }

    private fun checkIfUserHasCompletedOtpRequest(username: String) {
        if (!userRepository.existsUserByUsernameAndOtpVerifiedIsFalse(username)) {
            throw InvalidRegistrationException("Username $username did not complete OTP request.")
        }
    }

    private fun validateOtp(otpRequest: OtpRequest) {
        smsService.validateOtp(otpRequest)
    }

    fun registerNewUser(registrationRequest: RegistrationRequest) {
        checkIfUserIsAlreadyRegistered(registrationRequest.username)
        sendOtp(registrationRequest)
        registerUserWithOtpPending(registrationRequest)
    }

    private fun checkIfUserIsAlreadyRegistered(username: String) {
        if (userRepository.existsUserByUsernameAndOtpVerifiedIsTrue(username)) {
            throw InvalidRegistrationException("Username $username is already registered. Please login with credentials.")
        }
    }

    private fun sendOtp(registrationRequest: RegistrationRequest) {
        smsService.sendOtp(registrationRequest.username)
    }

    private fun registerUserWithOtpPending(registrationRequest: RegistrationRequest) {
        val username = registrationRequest.username
        val password = registrationRequest.password
        val otpNotVerifiedUser = User(username = username, password = password)

        val user = userRepository.findUserByUsername(registrationRequest.username).orElse(otpNotVerifiedUser)
        user.username = username
        user.password = password

        userRepository.save(user)
    }
}