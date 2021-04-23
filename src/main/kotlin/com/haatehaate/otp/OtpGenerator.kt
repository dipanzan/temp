package com.haatehaate.otp

interface OtpGenerator {
    fun generateOtp(length: Int): String
}