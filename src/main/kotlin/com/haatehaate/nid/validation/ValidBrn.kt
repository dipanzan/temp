package com.haatehaate.nid.validation

import com.haatehaate.utils.validator.Messages
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