package com.haatehaate.controller

import com.haatehaate.registration.RegistrationResponse
import com.haatehaate.registration.dto.RegistrationRequest
import com.haatehaate.service.OtpService
import com.haatehaate.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/user/registration")
class RegistrationController(
    private val userService: UserService,
    private val otpService: OtpService
) {
    @ResponseBody
    @PostMapping("/new")
    fun registerNewUser(@RequestBody @Valid registrationRequest: RegistrationRequest): ResponseEntity<Any> {
        userService.setupRegistrationForNewUser(registrationRequest)
        otpService.sendOtp(registrationRequest.username)

        val registrationResponse = RegistrationResponse(pending = RegistrationResponse.Pending(registrationRequest.username))
        return ResponseEntity.ok(registrationResponse)
    }

    @ResponseBody
    @PostMapping("/confirm-otp")
    fun confirmUserRegistrationWithOtp(@RequestBody @Valid otpRequest: OtpRequest) {

    }
 }