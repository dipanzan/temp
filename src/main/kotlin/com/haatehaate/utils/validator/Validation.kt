package com.haatehaate.utils.validator

sealed class Validation {
    object Success : Validation()
    data class Error(val reason: Any) : Validation()
}
