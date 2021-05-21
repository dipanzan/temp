package com.haatehaate.exception

import com.haatehaate.utils.validator.Messages

data class InvalidRegistrationException(val reason: String) : RuntimeException()

data class OtpException(val reason: String) : RuntimeException(reason)

data class InvalidLoginException(val reason: String) : RuntimeException(reason)

data class InvalidVerificationRequest(private val reason: String) : RuntimeException(reason)

data class UsernameNotFoundException(val reason: String = Messages.USERNAME_NOT_FOUND) : java.lang.RuntimeException()