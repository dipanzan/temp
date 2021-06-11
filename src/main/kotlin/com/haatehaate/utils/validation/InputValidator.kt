package com.haatehaate.utils.validation

import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import org.passay.*
import org.springframework.stereotype.Component


@Component
class InputValidator {
    companion object {
        private const val REGION_BD = "BD"
        private const val USERNAME = "username"
        private const val PASSWORD = "password"
        private const val CONFIRMED_PASSWORD = "confirmed_password"
        private const val INVALID_PHONE_NUMBER = "Invalid phone number, should consist of 13 digits."
        private const val PASSWORDS_DO_NOT_MATCH = "Passwords do not match."
        private const val INVALID_REGISTRATION = "Invalid registration parameters."

        private val passwordValidator = PasswordValidator(
            listOf(
                LengthRule(8, 255),
                WhitespaceRule(),
                CharacterRule(EnglishCharacterData.UpperCase, 1),
                CharacterRule(EnglishCharacterData.LowerCase, 1),
                CharacterRule(EnglishCharacterData.Digit, 1),
                CharacterRule(EnglishCharacterData.Special, 1)
            )
        )
    }

    fun isValidUsername(username: String): Boolean {
        return try {
            val phoneNumberUtil = PhoneNumberUtil.getInstance()
            val phone = phoneNumberUtil.parse(username, REGION_BD)

            return phoneNumberUtil.isValidNumber(phone)
        } catch (e: NumberParseException) {
            false
        }
    }

    fun isValidPassword(password: String): Boolean {
        val passwordData = PasswordData(password)
        val ruleResult: RuleResult = passwordValidator.validate(passwordData)

        return ruleResult.isValid
    }

    fun passwordEqualsConfirmedPassword(password: String, confirmedPassword: String) =
        password.contentEquals(confirmedPassword)

    fun isValidOtp(otp: String): Boolean {
        return otp.toIntOrNull()?.let {
            otp.length == 5
        } ?: false
    }
}