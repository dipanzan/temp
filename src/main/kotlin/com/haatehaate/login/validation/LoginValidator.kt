package com.haatehaate.login.validation

import com.haatehaate.login.LoginRequest
import com.haatehaate.utils.validator.InputValidator
import com.haatehaate.utils.validator.Messages
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class LoginValidator(
    private val inputValidator: InputValidator
) : ConstraintValidator<ValidLogin, LoginRequest> {
    private var message = ""

    override fun isValid(
        loginRequest: LoginRequest?,
        context: ConstraintValidatorContext
    ): Boolean {
        return validateLoginRequest(loginRequest, context)
    }

    override fun initialize(constraintAnnotation: ValidLogin) {
        this.message = constraintAnnotation.message
    }

    private fun validateLoginRequest(loginRequest: LoginRequest?, context: ConstraintValidatorContext): Boolean {
        return loginRequest?.let {
            buildConstraintMessage(loginRequest, context)
            processConstraints(context)
        } ?: false
    }

    private fun buildConstraintMessage(loginRequest: LoginRequest, context: ConstraintValidatorContext) {
        message = if (loginRequest.username.isBlank() && loginRequest.password.isBlank()) {
            context.defaultConstraintMessageTemplate
        } else if (!inputValidator.isValidUsername(loginRequest.username)) {
            Messages.VALID_PHONE
        } else if (!inputValidator.isValidPassword(loginRequest.password)) {
            Messages.VALID_PASSWORD
        } else {
            ""
        }
    }

    private fun processConstraints(context: ConstraintValidatorContext): Boolean {
        if (message.isNotEmpty()) {
            context.apply {
                disableDefaultConstraintViolation()
                buildConstraintViolationWithTemplate(message).addConstraintViolation()
            }
            return false
        }
        return true
    }
}