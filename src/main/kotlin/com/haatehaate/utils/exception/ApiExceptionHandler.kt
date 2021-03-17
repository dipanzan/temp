package com.haatehaate.utils.exception

import com.haatehaate.login.InvalidLoginException
import com.haatehaate.login.LoginResponse
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

import org.springframework.http.ResponseEntity

import java.util.HashMap

import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.context.request.WebRequest

import org.springframework.http.HttpHeaders

@Slf4j
@ControllerAdvice
class ApiExceptionHandler : ResponseEntityExceptionHandler() {
   /* @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<Any> {
        val errors: MutableMap<String, String?> = HashMap()

        System.err.println(ex.bindingResult.nestedPath)

        ex.bindingResult.allErrors.forEach { error: ObjectError ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.getDefaultMessage()
            errors[fieldName] = errorMessage
        }

        return ResponseEntity.badRequest().body(null)
    }*/

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {

        val errorMap: MutableMap<String, String> = HashMap()

        ex.bindingResult.fieldErrors.forEach {
            errorMap.put(it.field, it.defaultMessage ?: "")
        }

        val errorDetails = ErrorDetails(
            "Validation Failed",
            errorMap
        )

        return ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(InvalidLoginException::class)
    private fun handleInvalidLogin(ex: InvalidLoginException): ResponseEntity<Any> {
        val loginResponse = LoginResponse(error = ex.reason)
        return ResponseEntity.badRequest().body(loginResponse)
    }
}