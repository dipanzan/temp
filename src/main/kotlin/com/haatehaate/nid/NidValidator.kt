package com.haatehaate.nid

import com.haatehaate.nid.request.UserVerificationRequest
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class NidValidator : ConstraintValidator<ValidNid, UserVerificationRequest.Nid> {
    companion object {
        private const val NID_LENGTH_10 = 10
        private const val NID_LENGTH_13 = 13
        private const val NID_LENGTH_17 = 17
    }

    override fun isValid(
        userVerificationRequestNid: UserVerificationRequest.Nid?,
        context: ConstraintValidatorContext
    ): Boolean {
        return userVerificationRequestNid?.let {
            it.nid.length == NID_LENGTH_10
                    || it.nid.length == NID_LENGTH_13
                    || it.nid.length == NID_LENGTH_17
        } ?: false
    }
}