package com.haatehaate.exception.handlers

import com.haatehaate.nid.controller.VerificationController
import com.haatehaate.nid.dto.VerificationResponse
import com.haatehaate.nid.exceptions.VerificationException
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
    private fun handleWebClientResponseException(ex: WebClientResponseException): ResponseEntity<Any> {
        val body = ex.responseBodyAsString

        return ResponseEntity.


    }
       */


    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(VerificationException::class)
    private fun handleVerificationException(ex: VerificationException): Unit {
        log.debug(ex.reason)

    }
}