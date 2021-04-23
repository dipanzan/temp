package com.haatehaate.exception

data class InvalidRegistrationException(
    val reason: Any
) : RuntimeException()