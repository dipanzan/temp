package com.haatehaate.login.validation

import com.haatehaate.login.LoginRequest
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext


class LoginValidator : ConstraintValidator<ValidLogin, LoginRequest> {
    override fun isValid(
        loginRequest: LoginRequest?,
        constraintValidatorContext: ConstraintValidatorContext?
    ): Boolean {
        return loginRequest?.let {
            it.username.isNotBlank() && it.password.isNotBlank()
        } ?: false
    }
}