package com.haatehaate.service

import com.haatehaate.exception.OtpSmsException
import com.haatehaate.otp.OnnorokomSmsApi
import com.haatehaate.otp.OtpGenerator
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriBuilder
import reactor.core.publisher.Mono

@Service
class OtpService(
    private val otpGenerator: OtpGenerator,
    private val onnorokomSmsApi: OnnorokomSmsApi,
    private val webclientBuilder: WebClient.Builder
) {
    private val webClient: WebClient = webclientBuilder
        .baseUrl(onnorokomSmsApi.base_url)
        .build()

    companion object {
        private const val OTP_LENGTH = 5
    }

    fun sendOtp(username: String) {
        val otpSms = generateOtpSmsFor(username)
        val smsRequest = prepareSmsRequest(username, otpSms)
        val smsResponse = smsRequest.block()

        handleSmsError(smsResponse!!)
    }

    private fun generateOtpSmsFor(username: String): String {
        val otp = otpGenerator.generateOtp(OTP_LENGTH)
        System.err.println("OTP Generated: " + otp)

        return "Dear $username, your OTP for HaateHaate is: $otp"
    }

    private fun prepareSmsRequest(username: String, sms: String): Mono<String> {
        return webClient.get().uri {
            it.apply {
                queryParam(OnnorokomSmsApi.OPERATION, onnorokomSmsApi.operation)
                queryParam(OnnorokomSmsApi.API_KEY, onnorokomSmsApi.api_key)
                queryParam(OnnorokomSmsApi.SMS_TYPE, onnorokomSmsApi.sms_type)
                queryParam(OnnorokomSmsApi.MOBILE, username)
                queryParam(OnnorokomSmsApi.SMS_TEXT, sms)
            }.build()
        }.retrieve().bodyToMono(String::class.java)
    }

    private fun handleSmsError(response: String) {
        val tokens = response.split(OnnorokomSmsApi.RESPONSE_DELIMITER)
        val statusCode = tokens[0].toInt()
        val phone = tokens[1]

        when(statusCode) {
            in OnnorokomSmsApi.ERROR_START..OnnorokomSmsApi.ERROR_END -> throw OtpSmsException("hello")
        }
    }
}