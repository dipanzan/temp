package com.haatehaate.api.nid.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.haatehaate.api.nid.validation.ValidBrn
import com.haatehaate.api.nid.validation.ValidNid
import com.haatehaate.utils.message.Messages
import com.haatehaate.utils.validation.ValidUsername
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

enum class VerificationType {
    NID, BRN
}

@JsonInclude(JsonInclude.Include.NON_NULL)
data class VerificationRequest(
    @get:NotNull
    @get:DateTimeFormat(pattern = "yyyy-MM-dd")
    val dateOfBirth: LocalDate,

    @get:NotBlank(message = Messages.VALID_FULLNAME)
    val fullname: String,

    @get:ValidUsername
    val username: String,

    @get:NotNull(message = Messages.VALID_VERIFICATION_TYPE)
    val verificationType: VerificationType,

    @get:Valid
    val nid: Nid? = null,

    //@get:Valid
    val brn: Brn? = null
) {
    data class Nid(
        @get:ValidNid
        val nid: String,
        val nidCardFront: ByteArray? = null,
        val nidCardBack: ByteArray? = null
    )

    data class Brn(
        @get:ValidBrn
        val birthRegistrationNumber: String,
        val brnPicture: ByteArray? = null
    )
}

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