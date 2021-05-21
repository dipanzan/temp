package com.haatehaate.otp

import com.haatehaate.validation.credentials.ValidOtp
import com.haatehaate.validation.credentials.ValidUsername

data class OtpRequest(
    @get:ValidUsername
    val username: String,
    @get:ValidOtp
    val otp: String
)