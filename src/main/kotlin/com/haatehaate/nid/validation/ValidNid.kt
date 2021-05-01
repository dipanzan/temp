package com.haatehaate.nid.validation

import com.haatehaate.nid.validation.NidValidator
import com.haatehaate.utils.validator.Messages
import javax.validation.Constraint
import kotlin.reflect.KClass

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [NidValidator::class])
@Target(AnnotationTarget.PROPERTY_GETTER)
annotation class ValidNid(
    val message: String = Messages.VALID_NATIONAL_IDENTIFICATION_NO,
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = []
)