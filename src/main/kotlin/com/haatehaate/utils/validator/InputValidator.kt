package com.haatehaate.utils.validator

import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.haatehaate.registration.InvalidRegistrationException
import com.haatehaate.registration.dto.RegistrationInfo
import lombok.extern.slf4j.Slf4j
import org.passay.LengthRule

import org.passay.PasswordValidator
import org.passay.RuleResult

import org.passay.PasswordData

import org.passay.EnglishCharacterData

import org.passay.CharacterRule

import org.passay.WhitespaceRule


@Slf4j
class InputValidator : Validator {
    companion object {
        private const val REGION_BD = "BD"

        // Error Messages
        private const val PHONE = "phone"
        private const val PASSWORD = "password"
        private const val CONFIRMED_PASSWORD = "confirmed_password"
        private const val INVALID_PHONE_NUMBER = "Invalid phone number"
        private const val PASSWORDS_DO_NOT_MATCH = "Passwords do not match"
        private const val INVALID_REGISTRATION = "Invalid registration parameters"

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

    override var validationResult: Map<String, Validation> = emptyMap()

    override fun validate(inputs: Any): Validation {
        if (inputs !is RegistrationInfo) {
            throw InvalidRegistrationException(INVALID_REGISTRATION)
        }

        validationResult = mapOf(
            PHONE to isValidPhone(inputs.phone),
            PASSWORD to isValidPassword(inputs.password),
            CONFIRMED_PASSWORD to isValidPasswords(inputs.password, inputs.confirmedPassword)
        )

        validationResult.values.forEach {
            if (it is Validation.Error) return Validation.Error(INVALID_REGISTRATION)
        }
        return Validation.Success
    }

    private fun isValidPhone(phone: String): Validation {
        return try {
            val phoneNumberUtil = PhoneNumberUtil.getInstance()
            val phone = phoneNumberUtil.parse(phone, REGION_BD)

            return if (phoneNumberUtil.isValidNumber(phone)) {
                Validation.Success
            } else {
                Validation.Error(INVALID_PHONE_NUMBER)
            }
        } catch (e: NumberParseException) {
            Validation.Error(INVALID_PHONE_NUMBER)
        }
    }

    private fun isValidPassword(password: String): Validation {
        val passwordData = PasswordData(password)
        val ruleResult: RuleResult = passwordValidator.validate(passwordData)

        return if (ruleResult.isValid) {
            Validation.Success
        } else {
            Validation.Error(passwordValidator.getMessages(ruleResult))
        }
    }

    private fun isValidPasswords(password: String, confirmedPassword: String): Validation {
        return if (password.contentEquals(confirmedPassword)) {
            Validation.Success
        } else {
            Validation.Error(PASSWORDS_DO_NOT_MATCH)
        }
    }
}