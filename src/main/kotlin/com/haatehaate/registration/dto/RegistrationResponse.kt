package com.haatehaate.registration.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.haatehaate.utils.validator.Messages

@JsonInclude(JsonInclude.Include.NON_NULL)
class RegistrationResponse(
    val success: Success? = null,
    val pendingOtp: PendingOtp? = null,
    val error: Error? = null
) {
    data class Success(
        val message: String = Messages.REGISTRATION_SUCCESS,
        val username: String,
        val token: String
    )
    data class PendingOtp(
        val message: String = Messages.PLEASE_CONFIRM_OTP,
        val username: String
    )
    data class Error(
        val message: String = Messages.REGISTRATION_FAILED,
        val reason: String
    )
}


