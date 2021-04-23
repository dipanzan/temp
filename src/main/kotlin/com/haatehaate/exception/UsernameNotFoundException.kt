package com.haatehaate.exception

import java.lang.RuntimeException

data class UsernameNotFoundException(
    val reason: String = "Username not found, please register an account."
) : RuntimeException()
