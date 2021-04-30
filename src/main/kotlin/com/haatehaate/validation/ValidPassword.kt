package com.haatehaate.validation

import com.haatehaate.utils.validator.Messages
import javax.validation.Constraint
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.PROPERTY_GETTER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [PasswordValidator::class])
annotation class ValidPassword(
    val message: String = Messages.VALID_PASSWORD,
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = [],
)