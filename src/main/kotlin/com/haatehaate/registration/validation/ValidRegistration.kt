package com.haatehaate.registration.validation

import javax.validation.Constraint
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [RegistrationValidator::class])
annotation class ValidRegistration(
    val message: String = "Please provide all correct registration info before proceeding.",
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = []
)
