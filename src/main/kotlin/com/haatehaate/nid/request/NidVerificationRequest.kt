package com.haatehaate.nid.request

import com.haatehaate.nid.ValidNid
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

@ValidNid
data class NidVerificationRequest(
    val nid: String,
    val fullname: String,
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    val dateOfBirth: LocalDate
)