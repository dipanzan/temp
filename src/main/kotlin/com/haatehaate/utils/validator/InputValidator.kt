package com.haatehaate.utils.validator

import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.haatehaate.registration.request.RegistrationRequest
import org.passay.*
import org.springframework.stereotype.Component

interface RegistrationValidator {
    fun validateRegistrationRequest(registrationRequest: RegistrationRequest): Map<String, Validation>
}

@Component
class InputValidator : RegistrationValidator {
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

    override fun validateRegistrationRequest(registrationRequest: RegistrationRequest): Map<String, Validation> {
        val validatedRegistration = mutableMapOf<String, Validation>()

        val validUsername = isValidUsername1(registrationRequest.username)
        val validPassword = isValidPassword1(registrationRequest.password)
        val validConfirmedPassword =
            passwordEqualsConfirmedPassword(registrationRequest.password, registrationRequest.confirmedPassword)

        validatedRegistration.putIfAbsent(USERNAME, validUsername)
        validatedRegistration.putIfAbsent(PASSWORD, validPassword)
        validatedRegistration.putIfAbsent(CONFIRMED_PASSWORD, validConfirmedPassword)

        return validatedRegistration
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

    private fun isValidUsername1(username: String): Validation {
        return try {
            val phoneNumberUtil = PhoneNumberUtil.getInstance()
            val phone = phoneNumberUtil.parse(username, REGION_BD)

            return if (phoneNumberUtil.isValidNumber(phone)) {
                Validation.Success
            } else {
                Validation.Error(INVALID_PHONE_NUMBER)
            }
        } catch (e: NumberParseException) {
            Validation.Error(INVALID_PHONE_NUMBER)
        }
    }

    private fun isValidPassword1(password: String): Validation {
        val passwordData = PasswordData(password)
        val ruleResult: RuleResult = passwordValidator.validate(passwordData)

        return if (ruleResult.isValid) {
            Validation.Success
        } else {
            Validation.Error(passwordValidator.getMessages(ruleResult))
        }
    }

    private fun passwordEqualsConfirmedPassword(password: String, confirmedPassword: String): Validation {
        return if (password.contentEquals(confirmedPassword)) {
            Validation.Success
        } else {
            Validation.Error(PASSWORDS_DO_NOT_MATCH)
        }
    }
}