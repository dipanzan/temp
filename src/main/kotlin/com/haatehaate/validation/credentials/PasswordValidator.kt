package com.haatehaate.validation.credentials

import com.haatehaate.utils.validator.InputValidator
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class PasswordValidator(
    private val inputValidator: InputValidator
) : ConstraintValidator<ValidPassword, String> {

    override fun isValid(password: String?, context: ConstraintValidatorContext): Boolean {
        return password?.let {
            inputValidator.isValidPassword(it)
        } ?: false
    }
}