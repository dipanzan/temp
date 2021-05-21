package com.haatehaate.validation.credentials

import com.haatehaate.utils.validator.Messages
import javax.validation.Constraint
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.PROPERTY_GETTER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UsernameValidator::class])
annotation class ValidUsername(
    val message: String = Messages.VALID_PHONE,
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = []
)

@MustBeDocumented
@Target(AnnotationTarget.PROPERTY_GETTER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [PasswordValidator::class])
annotation class ValidPassword(
    val message: String = Messages.VALID_PASSWORD,
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = []
)

@MustBeDocumented
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [PasswordsMatchValidator::class])
annotation class ValidBothPasswords(
    val message: String = Messages.PASSWORDS_MATCH,
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = []
)

@MustBeDocumented
@Target(AnnotationTarget.PROPERTY_GETTER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [OtpValidator::class])
annotation class ValidOtp(
    val message: String = Messages.VALID_OTP,
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = []
)