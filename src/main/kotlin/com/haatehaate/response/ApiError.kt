package com.haatehaate.response

class ApiError private constructor(
    val error: String,
    val details: List<Any>?
) {

    data class ApiErrorBuilder(
        var error: String = "",
        var details: List<Any>? = null
    ) {
        fun error(error: String = "") = apply { this.error = error }
        fun details(details: List<Any> = emptyList()) = apply { this.details = details }
        fun build() = ApiError(error, details)
    }
}
