package com.haatehaate.registration.validation

import com.haatehaate.registration.dto.RegistrationRequest
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class RegistrationValidator : ConstraintValidator<ValidRegistration, RegistrationRequest> {
    override fun isValid(
        registrationRequest: RegistrationRequest?,
        constraintValidatorContext: ConstraintValidatorContext?
    ): Boolean {
        return registrationRequest?.let {
            it.username.isNotBlank() && it.password.isNotBlank() && it.confirmedPassword.isNotBlank()
        } ?: false
    }
}