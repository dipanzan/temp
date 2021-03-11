package com.haatehaate.utils.exception

import com.haatehaate.registration.InvalidRegistrationException
import com.haatehaate.response.ApiError
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

import org.springframework.http.ResponseEntity


@ControllerAdvice
class ApiExceptionHandler : ResponseEntityExceptionHandler() {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidRegistrationException::class)
    fun handleInvalidRegistration(ex: InvalidRegistrationException): ResponseEntity<Any> {
        val apiError = ApiError.ApiErrorBuilder()
            .error()
            .details(listOf())
            .build()

        return ResponseEntity
            .badRequest()
            .body(apiError)
    }
}