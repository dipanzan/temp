package com.haatehaate.nid.validation

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class BrnValidator : ConstraintValidator<ValidBrn, String> {
    companion object {
        private const val BRN_LENGTH_17 = 17
    }

    override fun isValid(brn: String?, context: ConstraintValidatorContext): Boolean {
        return brn?.let {
            it.length == BRN_LENGTH_17
        } ?: false
    }
}