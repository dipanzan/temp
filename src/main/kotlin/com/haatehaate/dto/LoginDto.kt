package com.haatehaate.login.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.haatehaate.utils.validation.ValidPassword
import com.haatehaate.utils.validation.ValidUsername
import java.time.LocalDateTime

data class LoginRequest(
    @get:ValidUsername
    val username: String,

    @get:ValidPassword
    val password: String
)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class LoginResponse(
    val success: Success? = null,
    val error: Error? = null
) {
    data class Success(val username: String, val tokenId: String, val tokenValidTill: LocalDateTime)
    data class Error(val message: String, val path: String? = null)
}