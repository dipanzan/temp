package com.haatehaate.utils.validator

interface Validator {
    fun validate(inputs: Any): Validation
}