package com.haatehaate.utils.exception

import com.haatehaate.registration.InvalidCredentialsException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.http.HttpHeaders

import org.springframework.web.context.request.WebRequest

import org.springframework.http.ResponseEntity


@ControllerAdvice
class GlobalExceptionController : ResponseEntityExceptionHandler() {
    companion object {
        private val BAD_REQUEST = HttpStatus.BAD_REQUEST
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [BadRequestException::class, InvalidCredentialsException::class/*, RuntimeException::class*/])
    fun handleBadRequest(ex: RuntimeException, request: WebRequest): ResponseEntity<Any> {
        val errorMessage = ex.message ?: BAD_REQUEST.toString()
        val errorResponse = ErrorResponse(errorMessage, BAD_REQUEST, BAD_REQUEST.value())

        return handleExceptionInternal(
            ex, errorResponse,
            HttpHeaders(),
            BAD_REQUEST,
            request
        )

    }
}