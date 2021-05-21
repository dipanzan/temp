package com.haatehaate.validation.credentials

import com.haatehaate.otp.OtpRequest
import com.haatehaate.registration.dto.RegistrationRequest
import com.haatehaate.utils.validator.InputValidator
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class UsernameValidator(
    private val inputValidator: InputValidator
) : ConstraintValidator<ValidUsername, String> {
    override fun isValid(username: String?, context: ConstraintValidatorContext): Boolean {
        return username?.let {
            inputValidator.isValidUsername(username)
        } ?: false
    }
}

class PasswordValidator(
    private val inputValidator: InputValidator
) : ConstraintValidator<ValidPassword, String> {
    override fun isValid(password: String?, context: ConstraintValidatorContext): Boolean {
        return password?.let {
            inputValidator.isValidPassword(it)
        } ?: false
    }
}

class PasswordsMatchValidator(
    private val inputValidator: InputValidator
) : ConstraintValidator<ValidBothPasswords, RegistrationRequest> {
    override fun isValid(registrationRequest: RegistrationRequest?, context: ConstraintValidatorContext): Boolean {
        return registrationRequest?.let {
            inputValidator.passwordEqualsConfirmedPassword(it.password, it.confirmedPassword)
        } ?: false
    }
}

class OtpValidator(
    private val inputValidator: InputValidator
) : ConstraintValidator<ValidOtp, String> {
    override fun isValid(otp: String?, context: ConstraintValidatorContext): Boolean {
        return otp?.let {
            inputValidator.isValidOtp(otp)
        } ?: false
    }

}