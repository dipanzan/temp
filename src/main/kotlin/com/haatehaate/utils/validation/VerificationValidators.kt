package com.haatehaate.api.nid.validation

import com.haatehaate.api.nid.dto.VerificationRequest
import com.haatehaate.api.nid.dto.VerificationType
import java.util.*
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class UserVerificationValidator : ConstraintValidator<ValidUserVerification, VerificationRequest> {
    private var message = ""

    override fun initialize(constraintAnnotation: ValidUserVerification) {
        this.message = constraintAnnotation.message
    }

    override fun isValid(
        verificationRequest: VerificationRequest?,
        context: ConstraintValidatorContext
    ): Boolean {
        return verificationRequest?.let {
            buildConstraintMessage(it)
            processConstraints(context)
        } ?: false
    }

    private fun buildConstraintMessage(verificationRequest: VerificationRequest) {
        when (verificationRequest.verificationType) {
            VerificationType.NID -> checkNid(verificationRequest.nid)
            VerificationType.BRN -> checkBrn(verificationRequest.brn)
        }
    }

    private fun checkNid(nid: VerificationRequest.Nid?) {
        if (Objects.isNull(nid)) {
            message = "Please provide NID"
        }
    }

    private fun checkBrn(brn: VerificationRequest.Brn?) {
        if (Objects.isNull(brn)) {
            message = "Please provide BRN"
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