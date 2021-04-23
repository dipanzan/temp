package com.haatehaate.otp

interface SendSmsAble {
    fun sendSms(mobile: String, sms: String)
}