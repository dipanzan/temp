package com.haatehaate.registration

import com.haatehaate.otp.OtpRequest
import com.haatehaate.otp.ResendOtpRequest
import com.haatehaate.registration.dto.RegistrationRequest
import com.haatehaate.registration.dto.RegistrationResponse
import com.haatehaate.validation.credentials.ValidUsername
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/user/registration")
class RegistrationController(
    private val registrationService: RegistrationService
) {
    companion object {
        const val USER_REGISTRATION_NEW = "/user/registration/new"
    }
    @ResponseBody
    @PostMapping("/new")
    fun registerNewUser(@RequestBody @Valid registrationRequest: RegistrationRequest): RegistrationResponse {
        registrationService.registerNewUser(registrationRequest)
        return RegistrationResponse(pendingOtp = RegistrationResponse.PendingOtp(username = registrationRequest.username))
    }

    @ResponseBody
    @PostMapping("/confirm-otp")
    fun confirmUserRegistrationWithOtp(@RequestBody @Valid otpRequest: OtpRequest): RegistrationResponse {
        val user = registrationService.completeUserRegistration(otpRequest)
        return RegistrationResponse(success = RegistrationResponse.Success(username = user.username, otpVerified = user.otpVerified, token = user.token!!.value!!))
    }

    @ResponseBody
    @PostMapping("/resend-otp")
    fun resendOtp(@RequestBody @Valid resendOtpRequest: ResendOtpRequest): RegistrationResponse {
        registrationService.retryRegistrationWithNewOtp(resendOtpRequest.username)
        return RegistrationResponse(pendingOtp = RegistrationResponse.PendingOtp(username = resendOtpRequest.username))
    }
 }