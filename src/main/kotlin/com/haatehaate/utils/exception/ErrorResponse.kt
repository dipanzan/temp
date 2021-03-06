package com.haatehaate.utils.exception

import org.springframework.http.HttpStatus

data class ErrorResponse(
    val message: String,
    val httpStatus: HttpStatus,
    val httpCode: Int
)