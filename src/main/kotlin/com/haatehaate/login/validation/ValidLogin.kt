package com.haatehaate.login.validation

import javax.validation.Constraint
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [LoginValidator::class])
annotation class ValidLogin(
    val message: String = "Please provide both phone number and password.",
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = []
)