package com.haatehaate.service

import com.haatehaate.entity.User
import com.haatehaate.api.sms.OtpRequest
import com.haatehaate.registration.dto.RegistrationRequest
import org.springframework.stereotype.Service

@Service
class RegistrationService(
    private val userService: UserService,
    private val smsService: SmsService
) {

    fun registerNewUser(registrationRequest: RegistrationRequest) {
        userService.checkIfUserIsAlreadyRegistered(registrationRequest.username)
        smsService.sendOtp(registrationRequest.username)
        userService.registerUserWithOtpPending(registrationRequest.username, registrationRequest.password)
    }

    fun completeUserRegistration(otpRequest: OtpRequest): User {
        userService.checkIfUserIsAlreadyRegistered(otpRequest.username)
        userService.checkIfUserHasCompletedOtpRequest(otpRequest.username)
        smsService.validateOtp(otpRequest)
        return userService.registerUserWithOtpVerified(otpRequest.username)
    }

    fun retryRegistrationWithNewOtp(username: String) {
        userService.checkIfUserIsAlreadyRegistered(username)
        userService.checkIfUserHasCompletedOtpRequest(username)
        smsService.sendOtp(username)
    }
}