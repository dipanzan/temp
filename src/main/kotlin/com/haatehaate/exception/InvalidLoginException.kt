package com.haatehaate.exception

data class InvalidLoginException(
    val reason: String
) : RuntimeException(reason)