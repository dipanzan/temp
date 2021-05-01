package com.haatehaate.nid.validation

import com.haatehaate.utils.validator.Messages
import javax.validation.Constraint
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UserVerificationValidator::class])
annotation class ValidUserVerification(
    val message: String = Messages.PROVIDE_VALID_USER_VERIFICATION_DATA,
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = [],
)