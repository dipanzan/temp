package com.haatehaate.api.sms

import com.haatehaate.utils.validator.logger
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "sms.provider")
data class SmsApi(
    val base_url: String,
    val api: String,
    val api_key: String,
    val op_key: String,
    val operation: String,
    val sms_type_key: String,
    val sms_type: String,
    val mobile_key: String,
    val sms_text_key: String,
    val response_separator: String
) {
    companion object {
        private val log = logger()
    }

    fun parseSmsResponse(response: String?): Response? {
        return response?.let {
            val tokens = it.split(response_separator)
            val responseCode = tokens[0].toInt()
            val recipientNumber = tokens[1]
            val responseId = tokens[2]

            Response(responseCode, recipientNumber, responseId)
        }
    }

    fun getResponseStatus(code: Int): ResponseStatusCodes {
        return ResponseStatusCodes.values().single() { it.code == code }
    }

    data class Response(
        val responseCode: Int,
        val recipientNumber: String,
        val responseId: String
    ) {
        fun isSmsSentSuccessfully() = responseCode == ResponseStatusCodes.SUCCESS.code
    }

    enum class ResponseStatusCodes(val code: Int) {
        SUCCESS(1900),
        PARAMETER_CONTENT_MISSING(1901),
        INVALID_USER_OR_PASS(1902),
        NOT_ENOUGH_BALANCE(1903),
        INVALID_DESTINATION_NUMBER(1905),
        OPERATOR_NOT_FOUND(1906),
        INVALID_MASK_NAME(1907),
        SMS_BODY_TOO_LONG(1908),
        DUPLICATE_CAMPAIGN_NAME(1909),
        INVALID_MESSAGE(1910),
        TOO_MANY_SMS_REQUEST(1911);
    }
}