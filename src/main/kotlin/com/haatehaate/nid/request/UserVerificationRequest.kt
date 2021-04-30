package com.haatehaate.nid.request

import com.fasterxml.jackson.annotation.JsonInclude
import com.haatehaate.validation.ValidUsername
import com.haatehaate.nid.ValidNid
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull


@JsonInclude(JsonInclude.Include.NON_NULL)
data class UserVerificationRequest(
    @get:ValidUsername
    val username: String,

    val fullname: String,

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    val dateOfBirth: LocalDate,

    @get:NotBlank
    val verificationType: String,

    @ValidNid
    val nid: Nid? = null,
    val brn: Brn? = null
) {

    data class Nid(
        val nid: String,
        val nidCardFront: ByteArray? = null,
        val nidCardBack: ByteArray? = null
    )

    data class Brn(
        val birthRegistrationNo: Int,
        val brnPicture: ByteArray? = null
    )
}