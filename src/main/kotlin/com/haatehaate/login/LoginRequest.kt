package com.haatehaate.login

import com.haatehaate.login.validation.ValidLogin

@ValidLogin
data class LoginRequest(
    val username: String,
    val password: String
)