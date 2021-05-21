package com.haatehaate.nid.exceptions

data class VerificationException(val reason: String) : RuntimeException(reason)