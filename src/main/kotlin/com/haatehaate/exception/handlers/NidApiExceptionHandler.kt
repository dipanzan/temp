package com.haatehaate.exception.handlers

import com.haatehaate.controller.UserVerificationController
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.reactive.function.client.WebClientResponseException

@ControllerAdvice(assignableTypes = [UserVerificationController::class])
class NidApiExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WebClientResponseException::class)
    fun handleWebClientResponseException(ex: WebClientResponseException): Unit {
        System.err.println(ex.responseBodyAsString)
    }
}