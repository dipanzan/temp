package com.haatehaate.validation

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