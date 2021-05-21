package com.haatehaate.registration

import com.haatehaate.otp.OtpRequest
import com.haatehaate.registration.dto.RegistrationRequest
import com.haatehaate.registration.dto.RegistrationResponse
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/user/registration")
class RegistrationController(
    private val registrationService: RegistrationService
) {
    @ResponseBody
    @PostMapping("/new")
    fun registerNewUser(@RequestBody @Valid registrationRequest: RegistrationRequest): RegistrationResponse {
        registrationService.registerNewUser(registrationRequest)
        return RegistrationResponse(pendingOtp = RegistrationResponse.PendingOtp(username = registrationRequest.username))
    }

    @ResponseBody
    @PostMapping("/confirm-otp")
    fun confirmUserRegistrationWithOtp(@RequestBody @Valid otpRequest: OtpRequest): RegistrationResponse {
        registrationService.completeUserRegistration(otpRequest)


        return RegistrationResponse(success = RegistrationResponse.Success(username = "123", token = "123"))
    }

    @ResponseBody
    @PostMapping("/resend-otp")
    fun resendOtp(@RequestBody @Valid otpRequest: OtpRequest): RegistrationResponse {

        return RegistrationResponse()
    }
 }