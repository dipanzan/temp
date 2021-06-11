package com.haatehaate.api.sms

interface SendSmsAble {
    fun sendSms(mobile: String, sms: String)
}