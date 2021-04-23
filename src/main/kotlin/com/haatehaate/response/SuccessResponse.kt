package com.haatehaate.response

data class SuccessResponse(
    val success: Map<String, Any>
) : BaseResponse()