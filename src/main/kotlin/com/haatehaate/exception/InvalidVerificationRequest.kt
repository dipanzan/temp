package com.haatehaate.exception

data class InvalidVerificationRequest(
    private val reason: String
) : RuntimeException(reason)
