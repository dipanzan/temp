package com.haatehaate.otp

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "sms.provider")
data class OnnorokomSmsApi(
    val base_url: String,
    val api_key: String,
    val operation: String,
    val sms_type: String
) {
    companion object {
        const val OPERATION = "op"
        const val API_KEY = "apiKey"
        const val SMS_TYPE = "sms_type"
        const val MOBILE = "mobile"
        const val SMS_TEXT = "smsText"

        const val RESPONSE_DELIMITER = "||"
        const val ERROR_START = 1901
        const val ERROR_END = 1911
    }

    enum class ErrorCodes(val responseCode: Int) {
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

        companion object {
            fun getErrorMessage(responseCode: Int): String {
                values().forEach {
                    if (responseCode == it.responseCode) return it.toString()
                }
                return SUCCESS.toString()
            }
        }
    }
}