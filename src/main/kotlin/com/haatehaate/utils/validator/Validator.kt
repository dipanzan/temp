package com.haatehaate.utils.validator

interface Validator {
    var validationResult: Map<String, Validation>
    fun validate(inputs: Any): Validation
}