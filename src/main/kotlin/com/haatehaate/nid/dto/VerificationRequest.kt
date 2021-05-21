package com.haatehaate.nid.request

import com.fasterxml.jackson.annotation.JsonInclude
import com.haatehaate.nid.dto.VerificationType
import com.haatehaate.nid.validation.ValidBrn
import com.haatehaate.nid.validation.ValidNid
import com.haatehaate.utils.validator.Messages
import com.haatehaate.validation.credentials.ValidUsername
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

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





