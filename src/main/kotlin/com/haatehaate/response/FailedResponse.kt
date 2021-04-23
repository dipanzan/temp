package com.haatehaate.response

data class FailedResponse(
    val failed: Map<String, Any>
) : BaseResponse()