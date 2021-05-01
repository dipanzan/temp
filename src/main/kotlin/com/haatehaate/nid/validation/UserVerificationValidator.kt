package com.haatehaate.nid.validation

import com.haatehaate.nid.request.Brn
import com.haatehaate.nid.request.Nid
import com.haatehaate.nid.request.VerificationRequest
import com.haatehaate.nid.request.VerificationType
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

    private fun checkNid(nid: Nid?) {
        if (Objects.isNull(nid)) {
            message = "Please provide NID"
        }
    }

    private fun checkBrn(brn: Brn?) {
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