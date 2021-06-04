package com.haatehaate.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "SmsLog")
@Table(name = "sms_log")
data class SmsLog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "otp", length = 5, nullable = false)
    val otp: String,

    @Column(name = "response_code", length = 4, nullable = false)
    val responseCode: Int,

    @Column(name = "recipient_number", length = 14, nullable = false)
    val recipientNumber: String,

    @Column(name = "response_id", nullable = false)
    val responseId: String,

    @Column(name = "response_status", nullable = false)
    val responseStatus: String,

    @Column(name = "sms_sent_at")
    val smsSentAt: LocalDateTime = LocalDateTime.now()
)