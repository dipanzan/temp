package com.haatehaate.nid.validation

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class NidValidator : ConstraintValidator<ValidNid, String> {
    companion object {
        private const val NID_LENGTH_10 = 10
        private const val NID_LENGTH_13 = 13
        private const val NID_LENGTH_17 = 17
    }

    override fun isValid(nid: String?, context: ConstraintValidatorContext): Boolean {
        return nid?.let {
            it.length == NID_LENGTH_10 || it.length == NID_LENGTH_13 || it.length == NID_LENGTH_17
        } ?: false
    }
}