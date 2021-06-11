package com.haatehaate.utils.error.handler

import com.haatehaate.controller.VerificationController
import com.haatehaate.utils.error.VerificationException
import com.haatehaate.utils.validator.logger
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice(assignableTypes = [VerificationController::class])
class ExternalApiExceptionHandler {
    companion object {
        private val log = logger()
    }

    /*@ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WebClientResponseException::class)
    private fun handleWebClientResponseException(ex: WebClientResponseException) {
        val body = ex.responseBodyAsString
    }*/


    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(VerificationException::class)
    private fun handleVerificationException(ex: VerificationException) {
        log.debug(ex.reason)
    }
}