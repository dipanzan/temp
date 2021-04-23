package com.haatehaate.otp

import org.springframework.stereotype.Component

@Component
class InputLengthOtpGenerator : OtpGenerator {
    // example OTP: 62451

    override fun generateOtp(length: Int): String {
        val otp: StringBuilder = StringBuilder()

        (1..length).forEach {
            val digit = (0..9).random()
            otp.append(digit)
        }
        return otp.toString()
    }
}