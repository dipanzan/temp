package com.haatehaate.registration.controller

import com.haatehaate.registration.InvalidRegistrationException
import com.haatehaate.registration.model.RegistrationInfo
import com.haatehaate.registration.service.UserService
import com.haatehaate.response.FailedResponse
import com.haatehaate.response.SuccessResponse
import com.haatehaate.utils.validator.InputValidator
import com.haatehaate.utils.validator.Validation
import com.haatehaate.utils.validator.Validator
import lombok.extern.slf4j.Slf4j
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Slf4j
@RestController
@RequestMapping("/user/registration")
class RegistrationController(
    private val userService: UserService
) {

    @ResponseBody
    @PostMapping("/new")
    fun registerUser(@RequestBody registrationInfo: RegistrationInfo): ResponseEntity<Any> {
        val validator: Validator = InputValidator()
        val validationStatus = validator.validate(registrationInfo)

        val userExists = userService.existsUserByPhone(registrationInfo.phone)

        if (userExists) {
            println("USER PRESENT")
        } else {
            println("USER NOT PRESENT")
        }

        return when (validationStatus) {
            is Validation.Success -> ResponseEntity.ok().body(SuccessResponse(validator.validationResult))
            is Validation.Error -> ResponseEntity.ok().body(FailedResponse(validator.validationResult))
        }
    }

    private fun checkIfAccountAlreadyExists() {
    }

    @ResponseBody
    @PostMapping("/confirm-otp")
    fun temp(): ResponseEntity<Any> {
        throw InvalidRegistrationException("HAHA")
        TODO("TODO")
    }
}