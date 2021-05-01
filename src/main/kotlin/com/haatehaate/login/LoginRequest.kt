package com.haatehaate.login

import com.haatehaate.validation.credentials.ValidPassword
import com.haatehaate.validation.credentials.ValidUsername

data class LoginRequest(
    @get:ValidUsername
    val username: String,

    @get:ValidPassword
    val password: String
)