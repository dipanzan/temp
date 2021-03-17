package com.haatehaate.login.validation

import com.haatehaate.login.LoginForm
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext


class LoginValidator : ConstraintValidator<ValidLogin, LoginForm> {
    override fun isValid(
        loginForm: LoginForm?,
        constraintValidatorContext: ConstraintValidatorContext?
    ): Boolean {
        return loginForm?.let {
            it.phone.isNotBlank() && it.password.isNotBlank()
        } ?: false
    }
}