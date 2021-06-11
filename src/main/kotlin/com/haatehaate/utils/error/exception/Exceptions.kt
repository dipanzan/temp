package com.haatehaate.utils.error

import com.haatehaate.utils.message.Messages

data class InvalidRegistrationException(val reason: String, val path: String? = null) : RuntimeException(reason)

data class OtpException(val reason: String, val path: String? = null) : RuntimeException(reason)

data class InvalidLoginException(val reason: String, val path: String?) : RuntimeException(reason)

data class InvalidVerificationRequest(private val reason: String) : RuntimeException(reason)

data class UsernameNotFoundException(val reason: String = Messages.USERNAME_NOT_FOUND) : RuntimeException(reason)

data class VerificationException(val reason: String) : RuntimeException(reason)