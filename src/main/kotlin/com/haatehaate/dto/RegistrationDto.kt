package com.haatehaate.registration.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.haatehaate.utils.message.Messages
import com.haatehaate.utils.validation.ValidBothPasswords
import com.haatehaate.utils.validation.ValidPassword
import com.haatehaate.utils.validation.ValidUsername

@ValidBothPasswords
data class RegistrationRequest(
    @get:ValidUsername val username: String,
    @get:ValidPassword val password: String,
    @get:ValidPassword val confirmedPassword: String
)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class RegistrationResponse(
    val success: Success? = null,
    val pendingOtp: PendingOtp? = null,
    val error: Error? = null
) {
    data class Success(
        val message: String = Messages.REGISTRATION_SUCCESS,
        val username: String,
        val otpVerified: Boolean,
        val token: String,
        val path: String = "/user/login"
    )

    data class PendingOtp(
        val message: String = Messages.PLEASE_CONFIRM_OTP,
        val username: String,
        val path: String = "/user/registration/confirm-otp"

    )

    data class Error(
        val message: String = Messages.REGISTRATION_FAILED,
        val reason: String,
        val path: String? = null
    )
}