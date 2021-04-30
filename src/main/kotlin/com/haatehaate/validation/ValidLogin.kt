package com.haatehaate.validation

import com.haatehaate.utils.validator.Messages.PROVIDE_VALID_CREDENTIALS
import javax.validation.Constraint
import kotlin.reflect.KClass


@MustBeDocumented
@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [LoginValidator::class])
annotation class ValidLogin(
    val message: String = PROVIDE_VALID_CREDENTIALS,
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = [],
)