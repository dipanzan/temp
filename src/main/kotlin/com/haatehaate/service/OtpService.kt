package com.haatehaate.service

import com.haatehaate.exception.OtpSmsException
import com.haatehaate.otp.OnnorokomSmsApi
import com.haatehaate.otp.OtpGenerator
import com.haatehaate.utils.validator.logger
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
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
        private val log = logger()
    }

    fun sendOtp(username: String) {
        val otpSms = generateOtpSmsFor(username)
        val smsRequest = prepareSmsRequest(username, otpSms)
        val smsResponse = smsRequest.block()

        parseSmsResponse(smsResponse!!)
    }

    private fun generateOtpSmsFor(username: String): String {
        val otp = otpGenerator.generateOtp(OTP_LENGTH)
        log.debug("OTP Generated: $otp")

        return "Dear $username, your OTP is: $otp"
    }

    private fun prepareSmsRequest(username: String, sms: String): Mono<String> {
        return webClient.post().uri {
            it.apply {
                queryParam(OnnorokomSmsApi.OPERATION, onnorokomSmsApi.operation)
                queryParam(OnnorokomSmsApi.API_KEY, onnorokomSmsApi.api_key)
                queryParam(OnnorokomSmsApi.SMS_TYPE, onnorokomSmsApi.sms_type)
                queryParam(OnnorokomSmsApi.MOBILE, username)
                queryParam(OnnorokomSmsApi.SMS_TEXT, sms)
            }.build()
        }.retrieve().bodyToMono(String::class.java)
    }

    private fun parseSmsResponse(response: String) {
        val tokens = response.split(OnnorokomSmsApi.RESPONSE_DELIMITER)
        val responseCode = tokens[0].toInt()
        val recipientNumber = tokens[1]
        val responseId = tokens[2]

        log.debug("reponseCode: $responseCode, recipientNumber: $recipientNumber, responseId: $responseId")

        when (responseCode) {
            in OnnorokomSmsApi.ERROR_START..OnnorokomSmsApi.ERROR_END -> generateOtpSmsException(responseCode)
            else -> Unit
        }
    }

    @Throws(OtpSmsException::class)
    private fun generateOtpSmsException(responseCode: Int) {
        val otpApiError = OnnorokomSmsApi.ResponseCodes.getResponseStatus(responseCode)
        throw OtpSmsException(otpApiError)
    }
}