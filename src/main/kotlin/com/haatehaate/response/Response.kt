package com.haatehaate.response

import org.springframework.http.HttpStatus

abstract class Response() {
    abstract val message: String
    abstract val httpStatus: HttpStatus
    abstract val httpCode: Int
}
