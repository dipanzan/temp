package com.haatehaate.nid.api.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class NidPersonResponse(
    @JsonProperty("passKyc") val passKyc: String?,
    @JsonProperty("errorCode") val errorCode: String?
) : PorichoyApiResponse()