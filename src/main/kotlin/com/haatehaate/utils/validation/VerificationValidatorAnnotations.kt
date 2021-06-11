package com.haatehaate.api.nid.validation

import com.haatehaate.utils.message.Messages
import javax.validation.Constraint
import kotlin.reflect.KClass

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [BrnValidator::class])
@Target(AnnotationTarget.PROPERTY_GETTER)
annotation class ValidBrn(
    val message: String = Messages.VALID_BIRTH_REGISTRATION_NO,
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = []
)

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [NidValidator::class])
@Target(AnnotationTarget.PROPERTY_GETTER)
annotation class ValidNid(
    val message: String = Messages.VALID_NATIONAL_IDENTIFICATION_NO,
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = []
)

@MustBeDocumented
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UserVerificationValidator::class])
annotation class ValidUserVerification(
    val message: String = Messages.PROVIDE_VALID_USER_VERIFICATION_DATA,
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = [],
)