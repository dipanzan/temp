package com.haatehaate.login.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class LoginResponse(
    val success: Success? = null,
    val error: Error? = null
) {
    data class Success(val username: String, val token: String)
    data class Error(val message: String)
}