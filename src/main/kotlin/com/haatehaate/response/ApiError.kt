package com.haatehaate.response

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ApiError(
    val error: String,
    val reason: Any? = null
)
