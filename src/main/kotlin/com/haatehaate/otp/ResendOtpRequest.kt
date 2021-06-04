package com.haatehaate.otp

import com.haatehaate.validation.credentials.ValidUsername

data class ResendOtpRequest(
    @get:ValidUsername
    val username: String
)