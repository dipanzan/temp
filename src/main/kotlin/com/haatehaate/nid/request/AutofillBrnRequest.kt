package com.haatehaate.nid.request

import com.fasterxml.jackson.annotation.JsonProperty

data class AutofillBrnRequest(
    @JsonProperty("birth_registration_number")
    val brn: String,

    @JsonProperty("person_dob")
    val dateOfBirth: String,

    @JsonProperty("team_tx_id")
    val teamTransactionId: String? = null
)

