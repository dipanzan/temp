package com.haatehaate.nid.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class VerificationResponse(
    val verificationType: VerificationType,
    val success: Success? = null,
    val error: Error? = null
) {
    data class Success(
        val nid: String? = null,
        val brn: String? = null,
        val status: Status = Status.VERIFIED,
        val message: String,
    )
    data class Error(
        val status: Status = Status.UNVERIFIED,
        val reason: String
    )

    enum class Status {
        VERIFIED, UNVERIFIED
    }
}

