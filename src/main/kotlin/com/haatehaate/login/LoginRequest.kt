package com.haatehaate.login

import com.haatehaate.validation.ValidPassword
import com.haatehaate.validation.ValidUsername

data class LoginRequest(
    @get:ValidUsername
    val username: String,

    @get:ValidPassword
    val password: String
)