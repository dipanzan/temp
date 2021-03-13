package com.haatehaate.utils.exception

import com.haatehaate.registration.InvalidRegistrationException
import com.haatehaate.response.ApiError
import jdk.nashorn.internal.runtime.regexp.joni.Config.log
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError

import java.util.HashMap

import org.springframework.web.bind.MethodArgumentNotValidException
import java.util.function.Consumer

@Slf4j
@ControllerAdvice
class ApiExceptionHandler {

    companion object {
        private const val INVALID_REQUEST = "Invalid Request"
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidRegistrationException::class)
    fun handleInvalidRegistration(ex: InvalidRegistrationException): ResponseEntity<Any> {
        return ResponseEntity
            .badRequest()
            .body(ex.reason)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<Any> {
        val errors: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.forEach(Consumer { error: ObjectError ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.getDefaultMessage()
            errors[fieldName] = errorMessage
        })

        return ResponseEntity.badRequest().body(errors)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleMessageNotReadableExceptions(ex: HttpMessageNotReadableException): BadRequest {
        return BadRequest()
    }
}