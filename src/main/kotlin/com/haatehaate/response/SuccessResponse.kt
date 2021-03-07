package com.haatehaate.response

import org.springframework.http.HttpStatus

data class SuccessResponse(
        override val message: String,
        override val httpStatus: HttpStatus = HttpStatus.OK,
        override val httpCode: Int = HttpStatus.OK.value()
) : Response()
