package com.haatehaate.api.nid.dto.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class NidPersonPhotoResponse(
    @JsonProperty("photo") val photo: String?,
    @JsonProperty("passKyc") val passKyc: String?,
    @JsonProperty("errorCode") val errorCode: String?
) : PorichoyApiResponse()