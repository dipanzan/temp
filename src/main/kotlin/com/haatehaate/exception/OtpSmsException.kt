package com.haatehaate.exception

data class OtpSmsException(val reason: String) : RuntimeException(reason)
