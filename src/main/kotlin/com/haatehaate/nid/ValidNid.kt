package com.haatehaate.nid

import javax.validation.Constraint
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.CLASS, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [NidValidator::class])
annotation class ValidNid(
    val message: String = "NID must be 10, 13 or 17 digits.",
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = []
)