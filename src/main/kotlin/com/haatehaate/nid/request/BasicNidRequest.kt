package com.haatehaate.nid.request

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Builder

@Builder
data class BasicNidRequest(
    @JsonProperty("person_dob")
    val dateOfBirth: String,

    @JsonProperty("national_id")
    val nid: String,

    @JsonProperty("person_fullname")
    val fullname: String,

    @JsonProperty("team_tx_id")
    val teamTransactionId: String? = null,

    @JsonProperty("match_name")
    val matchName: Boolean = true
)