package com.haatehaate.response

import org.springframework.http.HttpStatus

data class ErrorResponse(
        override val message: String,
        override val httpStatus: HttpStatus = HttpStatus.BAD_REQUEST,
        override val httpCode: Int = HttpStatus.BAD_REQUEST.value()
) : Response()