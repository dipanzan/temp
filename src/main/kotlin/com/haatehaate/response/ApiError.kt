package com.haatehaate.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.haatehaate.utils.validator.Messages
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ApiError(
    val timestamp: LocalDateTime = LocalDateTime.now()
) {
    var status: HttpStatus? = null
    var message: String? = null
    var errors: List<ValidationError>? = null

    constructor(httpStatus: HttpStatus) : this() {
        this.status = httpStatus
        this.message = Messages.UNEXPECTED_ERROR
    }

    constructor(httpStatus: HttpStatus, message: String) : this() {
        this.status = httpStatus
        this.message = message
    }

    constructor(
        httpStatus: HttpStatus,
        message: String,
        errors: List<ValidationError>
    ) : this() {
        this.status = httpStatus
        this.message = message
        this.errors = errors
    }

    data class ValidationError(
        val field: String? = null,
        val message: String? = null
    )
}