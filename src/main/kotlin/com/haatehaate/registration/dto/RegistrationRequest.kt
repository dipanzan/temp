package com.haatehaate.registration.dto

import com.haatehaate.registration.validation.ValidRegistration

@ValidRegistration
data class RegistrationRequest(
    val username: String,
    val password: String,
    val confirmedPassword: String
)