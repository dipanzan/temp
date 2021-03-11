package com.haatehaate.utils.validator

import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.haatehaate.utils.exception.InvalidCredentialsException
import com.haatehaate.registration.model.RegistrationForm
import lombok.extern.slf4j.Slf4j
import java.util.regex.Pattern

@Slf4j
class RegistrationValidator : Validator {
    companion object {
        // Bangladeshi mobile phone numbers starting with +880, 880
        private const val PHONE_PATTERN = "^(?:\\+?88)?01[13-9]\\d{8}\$"
        private const val REGION_BD = "BD"

        // Password validation criteria
        private const val DIGIT = "(?=.*[0-9])"
        private const val LOWERCASE = "(?=.*[a-z])"
        private const val UPPERCASE = "(?=.*[A-Z])"
        private const val SPECIAL_CHARS = "(?=.*[@#\$%^&+=])"
        private const val NO_SPACES = "(?=\\\\S+\$)"
        private const val MIN_LENGTH = ".{2,}"

        // Password validation using above set criteria
        private const val PASSWORD_PATTERN = "^" +
                //DIGIT +
                LOWERCASE +
                //UPPERCASE +
                //SPECIAL_CHARS +
                MIN_LENGTH +
                "$"

        // Messages
        private const val FIELD_EMPTY = "Field cannot be empty"
        private const val INVALID_PHONE_NUMBER = "Invalid phone number"
        private const val PASSWORD_NOT_STRONG = "Password not strong enough"
        private const val PASSWORDS_DO_NOT_MATCH = "Passwords do not match"
        private const val INVALID_INPUT = "Invalid input"
    }

    override fun validate(form: Any): Validation {
        if (form is RegistrationForm) {
            return isValidCredential(form.phone, ::isValidPhone)
        } else {
            throw InvalidCredentialsException(INVALID_INPUT)
        }
    }

    private fun isValidCredential(input: String, rule: (input: String) -> Validation): Validation {
        return rule(input)
    }


    private fun isValidPhone(phoneNumber: String): Validation {
        return try {
            val phoneNumberUtil = PhoneNumberUtil.getInstance()
            val phone = phoneNumberUtil.parse(phoneNumber, REGION_BD)
            return if (phoneNumberUtil.isValidNumber(phone)) Validation.Success else Validation.Error(
                INVALID_PHONE_NUMBER
            )
        } catch (e: NumberParseException) {
            Validation.Error(INVALID_PHONE_NUMBER)
        }
    }

    private fun isValidPassword(input: String): Validation {
        if (input.isEmpty()) {
            return Validation.Error(FIELD_EMPTY)
        }
        return if (isValidPattern(
                input,
                PASSWORD_PATTERN
            )
        ) Validation.Success else Validation.Error(PASSWORD_NOT_STRONG)
    }

    private fun passwordsMatch(password: String, confirmedPassword: String): Validation {
        return if (password.contentEquals(confirmedPassword)) Validation.Success else Validation.Error(
            PASSWORDS_DO_NOT_MATCH
        )
    }

    private fun isValidPattern(input: String, pattern: String): Boolean {
        val pattern = Pattern.compile(pattern)
        val matcher = pattern.matcher(input)

        return matcher.matches()
    }
}