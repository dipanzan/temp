package com.haatehaate.registration

import com.haatehaate.utils.common.InputValidator
import com.haatehaate.utils.exception.BadRequestException
import lombok.extern.slf4j.Slf4j
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.*

@Slf4j
@RestController
@RequestMapping("/registration")
class RegistrationController(
    private val regisrationService: RegisrationService
) {

    @GetMapping("hello")
    fun hello(): String {
        throw BadRequestException("Bahahahaha!")
    }

    @ResponseBody
    @PostMapping("/new-user")
    fun registerUser(@RequestBody registrationForm: RegistrationForm): ResponseEntity<Any> {



        return ResponseEntity.ok("ALL GOOD")
    }

    private fun isValidUserCredentials(registrationForm: RegistrationForm) {

        InputValidator.isValidPhone(registrationForm.phone)
        registrationForm.phone
    }
}