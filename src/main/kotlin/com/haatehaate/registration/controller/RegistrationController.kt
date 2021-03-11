package com.haatehaate.registration.controller

import com.haatehaate.registration.InvalidRegistrationException
import com.haatehaate.registration.model.RegistrationForm
import com.haatehaate.registration.service.RegisrationService
import com.haatehaate.utils.validator.RegistrationValidator
import com.haatehaate.utils.exception.BadRequestException
import com.haatehaate.response.ErrorResponse
import com.haatehaate.response.SuccessResponse
import com.haatehaate.utils.validator.Validation
import com.haatehaate.utils.validator.Validator
import lombok.extern.slf4j.Slf4j
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Slf4j
@RestController
@RequestMapping("/registration")
class RegistrationController(
        private val regisrationService: RegisrationService
) {
    @GetMapping("/hello")
    fun hello(): String {
        throw BadRequestException("Bahahahaha!")
    }

    @ResponseBody
    @PostMapping("/new-user")
    fun registerUser(@RequestBody registrationForm: RegistrationForm): ResponseEntity<Any> {
        /*val validator: Validator = RegistrationValidator()
        val validatedForm = validator.validate(registrationForm)

        return when (validatedForm) {
            is Validation.Error -> ResponseEntity.ok(ErrorResponse(validatedForm.error))
            else -> ResponseEntity.ok(SuccessResponse("ALL GOOD"))
        }*/

        throw InvalidRegistrationException("HAHA")
    }
}