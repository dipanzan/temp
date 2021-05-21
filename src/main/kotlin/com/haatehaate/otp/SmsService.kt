package com.haatehaate.otp

import com.haatehaate.config.WebClientConfig
import com.haatehaate.entity.SmsLog
import com.haatehaate.exception.OtpException
import com.haatehaate.repository.SmsLogRepository
import com.haatehaate.utils.validator.Messages
import com.haatehaate.utils.validator.logger
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class SmsService(
    @Qualifier(WebClientConfig.SMS_WEB_CLIENT)
    private val webClient: WebClient,
    private val smsApi: SmsApi,
    private val smsLogRepository: SmsLogRepository
) {
    companion object {
        private val log = logger()
        private const val OTP_REQUEST_LIMIT = 10
    }

    fun validateOtp(otpRequest: OtpRequest) {
        if (smsLogRepository.existsSmsLogByRecipientNumberAndOtpNot(otpRequest.username, otpRequest.otp)) {
            throw OtpException(Messages.OTP_DID_NOT_MATCH)
        }
    }

    fun sendOtp(username: String) {
        if (smsLogRepository.countByRecipientNumber(username) > OTP_REQUEST_LIMIT) {
            throw OtpException(Messages.OTP_REQUESTED_TOO_MANY_TIMES)
        }

        val otp = generateFiveDigitOtp()
        val sms = generateOtpMessage(username, otp)
        val response = sendSmsRequest(username, sms)
        val smsApiResponse = parseSmsResponse(response)

        return smsApiResponse?.let {
            updateSmsLog(it, otp)
            checkIfOtpSentSuccessfully(it)
        } ?: throw OtpException(Messages.FAILED_TO_SEND_OTP)
    }

    private fun parseSmsResponse(response: String?) = smsApi.parseSmsResponse(response)

    private fun checkIfOtpSentSuccessfully(smsApiResponse: SmsApi.SmsApiResponse) {
        val smsResponseStatus = SmsApi.ResponseStatusCodes.getResponseStatus(smsApiResponse.responseCode)

        when (smsResponseStatus) {
            SmsApi.ResponseStatusCodes.SUCCESS -> Unit
            in SmsApi.ResponseStatusCodes.PARAMETER_CONTENT_MISSING..SmsApi.ResponseStatusCodes.TOO_MANY_SMS_REQUEST -> throw OtpException(
                Messages.FAILED_TO_SEND_OTP
            )
        }
    }

    private fun updateSmsLog(smsApiResponse: SmsApi.SmsApiResponse, otp: String) {
        val responseCode = smsApiResponse.responseCode
        val recipientNumber = smsApiResponse.recipientNumber
        val responseId = smsApiResponse.responseId
        val responseStatus = SmsApi.ResponseStatusCodes.getResponseStatus(responseCode).toString()

        val smsLog = SmsLog(
            responseCode = responseCode,
            recipientNumber = recipientNumber,
            responseId = responseId,
            responseStatus = responseStatus,
            otp = otp
        )
        smsLogRepository.save(smsLog)
    }

    private fun sendSmsRequest(username: String, sms: String): String? {
        return webClient.post()
            .uri {
                it.apply {
                    queryParam(smsApi.op_key, smsApi.operation)
                    queryParam(smsApi.api_key, smsApi.api)
                    queryParam(smsApi.sms_type_key, smsApi.sms_type)
                    queryParam(smsApi.mobile_key, username)
                    queryParam(smsApi.sms_text_key, sms)
                }.build()
            }.retrieve()
            .bodyToMono(String::class.java)
            .block()
    }


    private fun generateFiveDigitOtp(): String {
        val otp: StringBuilder = StringBuilder()
        (1..5).forEach {
            val digit = (1..9).random()
            otp.append(digit)
        }
        return otp.toString()
    }

    private fun generateOtpMessage(username: String, otp: String) = "Dear $username, your OTP for HaateHaate is: $otp"
}