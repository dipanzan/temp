package com.haatehaate.login.validation

import com.haatehaate.login.LoginInfo
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext


class LoginValidator : ConstraintValidator<ValidLogin, LoginInfo> {
    override fun isValid(
        loginInfo: LoginInfo?,
        constraintValidatorContext: ConstraintValidatorContext?
    ): Boolean {
        return loginInfo?.let {
            it.phone.isNotBlank() && it.password.isNotBlank()
        } ?: false
    }
}