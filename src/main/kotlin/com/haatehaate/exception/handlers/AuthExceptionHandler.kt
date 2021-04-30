package com.haatehaate.exception.handlers

import com.haatehaate.controller.LoginController
import com.haatehaate.controller.RegistrationController
import com.haatehaate.exception.InvalidLoginException
import com.haatehaate.exception.InvalidRegistrationException
import com.haatehaate.exception.OtpSmsException
import com.haatehaate.login.LoginResponse
import com.haatehaate.registration.RegistrationResponse
import lombok.extern.log4j.Log4j2
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice(assignableTypes = [LoginController::class, RegistrationController::class])
class AuthExceptionHandler {
    companion object {
        private const val MISSING_ARGUMENTS = "Missing Arguments"
    }

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
        return RegistrationResponse(error = RegistrationResponse.Error(ex.reason))
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OtpSmsException::class)
    fun handleOtpsSmsException(ex: OtpSmsException): RegistrationResponse {
        return RegistrationResponse(error = RegistrationResponse.Error(ex.reason))
    }
}