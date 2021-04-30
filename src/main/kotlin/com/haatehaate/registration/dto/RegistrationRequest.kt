package com.haatehaate.registration.dto

import com.haatehaate.registration.validation.ValidRegistration
import javax.validation.constraints.Size

@ValidRegistration
data class RegistrationRequest(
    @Size(min = 11, max = 14)
    val username: String,
    val password: String,
    val confirmedPassword: String
)