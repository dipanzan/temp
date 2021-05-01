package com.haatehaate.exception.handlers

import com.haatehaate.response.ApiError
import com.haatehaate.utils.validator.Messages
import com.haatehaate.utils.validator.logger
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ApiExceptionHandler(
    private val messageSource: MessageSource
) {
    companion object {
        private val log = logger()
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
    ): ApiError {
        log.debug(ex.stackTraceToString(), ex)
        return ApiError(HttpStatus.BAD_REQUEST, ex.localizedMessage)
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(
        ex: MethodArgumentNotValidException
    ): ApiError {
        log.debug(ex.stackTraceToString(), ex)
        return ApiError(HttpStatus.BAD_REQUEST, Messages.REQUEST_BODY_INVALID, extractFieldErrors(ex))
    }

    private fun extractFieldErrors(ex: MethodArgumentNotValidException): List<ApiError.ValidationError> {
        val bindingResult = ex.bindingResult
        val errors = bindingResult.allErrors
        val validationErrors = mutableListOf<ApiError.ValidationError>()

        errors.forEach {
            var field: String = it.objectName
            var message: String? = it.defaultMessage

            if (it is FieldError) {
                field = it.field
                message = it.defaultMessage ?: Messages.UNEXPECTED_ERROR
            }
            validationErrors.add(ApiError.ValidationError(field, message))
        }
        return validationErrors
    }
}