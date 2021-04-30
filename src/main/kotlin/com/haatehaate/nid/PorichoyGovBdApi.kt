package com.haatehaate.nid

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "nid.verification")
data class PorichoyGovBdApi(
    val api_key: String,
    val base_url: String
) {
    companion object {
        const val X_API_KEY = "x-api-key"
        const val PERSON_DOB = "person_dob"
        const val NATIONAL_ID = "national_id"
        const val PERSON_FULLNAME = "person_fullname"
        const val TEAM_TX_ID = "team_tx_id"
        const val MATCH_NAME = "match_name"

        const val TEST_NID_PERSON = "/test-nid-person"
        const val VERIFY_BIRTH_REGISTRATION = "/verify-birth-registration"
        const val NID_PERSON = "/nid-person"
    }
}