package com.haatehaate.exception

import com.haatehaate.response.ApiError
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ApiExceptionHandler {
    companion object {
        private const val REQUEST_BODY_INVALID = "Request body invalid."
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
    ): ApiError {
        return ApiError(REQUEST_BODY_INVALID, ex.localizedMessage)
    }
}