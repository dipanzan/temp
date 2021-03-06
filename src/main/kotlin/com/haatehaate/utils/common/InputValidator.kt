package com.haatehaate.utils.common

import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.haatehaate.registration.InvalidCredentialsException
import com.haatehaate.utils.exception.BadRequestException

object InputValidator {
    private const val INVALID_PHONE_NUMBER = "Invalid phone number"
    private const val PASSWORDS_DO_NOT_MATCH = "Passwords do not match"

    fun isValidPhone(phone: String): Boolean {
        val phoneNumberUtil = PhoneNumberUtil.getInstance()
        try {
            val phone = phoneNumberUtil.parse(phone, "BD")
            return phoneNumberUtil.isValidNumber(phone)
        } catch (e: NumberParseException) {
            System.err.println("NumberParseException was thrown: $e")
            throw InvalidCredentialsException(INVALID_PHONE_NUMBER)
        }
    }

    fun isValidPassword(password: String): Boolean {
        return true
    }

    fun passwordsMatch(password: String, confirmedPassword: String): Boolean {
        return if (password.contentEquals(confirmedPassword)) true
        else throw InvalidCredentialsException(PASSWORDS_DO_NOT_MATCH)
    }
}