package com.haatehaate.api.nid.dto.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class AutofillBrnResponse(
    @JsonProperty("registrationFound") val registrationFound: String?,
    @JsonProperty("birthRecord") val birthRecord: BirthRecord,
    @JsonProperty("errorCode") val errorCode: String?,
    @JsonProperty("errorResponse") val errorResponse: String?
) : PorichoyApiResponse() {
    data class BirthRecord(
        @JsonProperty("personNameBN") val personNameBN: String,
        @JsonProperty("personNameEN") val personNameEN: String,

        @JsonProperty("motherNameBN") val motherNameBN: String,
        @JsonProperty("motherNameEN") val motherNameEN: String,

        @JsonProperty("motherNatBN") val motherNatBN: String,
        @JsonProperty("motherNatEN") val motherNatEN: String,

        @JsonProperty("fatherNameBN") val fatherNameBN: String,
        @JsonProperty("fatherNameEN") val fatherNameEN: String,

        @JsonProperty("fatherNatBN") val fatherNatBN: String,
        @JsonProperty("fatherNatEN") val fatherNatEN: String,

        @JsonProperty("placeOfBirthBN") val placeOfBirthBN: String,
        @JsonProperty("placeOfBirthEN") val placeOfBirthEN: String,
    )
}