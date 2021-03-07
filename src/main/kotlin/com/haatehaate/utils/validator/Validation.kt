package com.haatehaate.utils.validator

sealed class Validation {
    object Success : Validation()
    data class Error(val error: String) : Validation()
}
