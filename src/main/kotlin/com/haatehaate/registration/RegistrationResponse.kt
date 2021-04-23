package com.haatehaate.registration

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class RegistrationResponse(
    val success: Success? = null,
    val pending: Pending? = null,
    val error: Error? = null
) {
    data class Success(
        val message: String = "Successfully completed registration.",
        val username: String,
        val token: String
    )
    data class Pending(
        val username: String,
        val message: String = "Please confirm registration with OTP."
    )
    data class Error(val message: Any)
}


