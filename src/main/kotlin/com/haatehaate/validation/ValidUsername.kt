package com.haatehaate.validation

import com.haatehaate.utils.validator.Messages
import javax.validation.Constraint
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.PROPERTY_GETTER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UsernameValidator::class])
annotation class ValidUsername(
    val message: String = Messages.VALID_PHONE,
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = [],
)
