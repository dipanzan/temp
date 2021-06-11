package com.haatehaate.api.sms

import com.fasterxml.jackson.annotation.JsonProperty
import com.haatehaate.utils.validation.ValidOtp
import com.haatehaate.utils.validation.ValidUsername

data class OtpRequest(
    @get:ValidUsername
    val username: String,
    @get:ValidOtp
    val otp: String
)

data class ResendOtpRequest(
    @get:ValidUsername
    @JsonProperty("username")
    val username: String
)