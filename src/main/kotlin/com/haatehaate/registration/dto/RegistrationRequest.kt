package com.haatehaate.registration.dto

import com.haatehaate.validation.credentials.ValidBothPasswords
import com.haatehaate.validation.credentials.ValidPassword
import com.haatehaate.validation.credentials.ValidUsername

@ValidBothPasswords
data class RegistrationRequest(
    @get:ValidUsername val username: String,
    @get:ValidPassword val password: String,
    @get:ValidPassword val confirmedPassword: String
)