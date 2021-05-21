package com.haatehaate.exception.handlers

import com.haatehaate.login.LoginController
import com.haatehaate.registration.RegistrationController
import com.haatehaate.exception.InvalidLoginException
import com.haatehaate.exception.InvalidRegistrationException
import com.haatehaate.exception.OtpException
import com.haatehaate.login.dto.LoginResponse
import com.haatehaate.registration.dto.RegistrationResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice(assignableTypes = [LoginController::class, RegistrationController::class])
class AuthExceptionHandler {
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): LoginResponse {
        val reason = ex.bindingResult.globalErrors.first().defaultMessage!!
        return LoginResponse(error = LoginResponse.Error(reason))
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidLoginException::class)
    fun handleInvalidLoginException(ex: InvalidLoginException): LoginResponse {
        return LoginResponse(error = LoginResponse.Error(ex.reason))
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidRegistrationException::class)
    fun handleInvalidRegistrationException(ex: InvalidRegistrationException): RegistrationResponse {
        return RegistrationResponse(error = RegistrationResponse.Error(reason = ex.reason))
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OtpException::class)
    fun handleOtpException(ex: OtpException): RegistrationResponse {
        return RegistrationResponse(error = RegistrationResponse.Error(reason = ex.reason))
    }
}