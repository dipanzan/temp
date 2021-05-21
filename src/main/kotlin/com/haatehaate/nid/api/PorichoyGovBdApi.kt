package com.haatehaate.nid.api

import com.haatehaate.nid.api.request.AutofillBrnRequest
import com.haatehaate.nid.api.request.NidPersonPhotoRequest
import com.haatehaate.nid.api.request.NidPersonRequest
import com.haatehaate.nid.api.request.PorichoyApiRequest
import com.haatehaate.nid.dto.VerificationType
import com.haatehaate.nid.request.VerificationRequest
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "nid.verification")
data class PorichoyGovBdApi(
    val api_key: String,
    val base_url: String
) {
    companion object {
        const val X_API_KEY_HEADER = "x-api-key"
        const val PERSON_DOB = "person_dob"
        const val NATIONAL_ID = "national_id"
        const val PERSON_FULLNAME = "person_fullname"
        const val TEAM_TX_ID = "team_tx_id"
        const val MATCH_NAME = "match_name"

        const val NID_PERSON_URL = "/nid-person"
        const val NID_PERSON_PHOTO_URL = "/nid-person-photo"
        const val VERIFY_BIRTH_REGISTRATION_URL = "/verify-birth-registration"
        const val TEST_NID_PERSON_URL = "/test-nid-person"
    }

    fun prepareApiRequest(verificationRequest: VerificationRequest): PorichoyApiRequest {
        return when (verificationRequest.verificationType) {
            VerificationType.NID -> prepareNidPersonRequest(verificationRequest)
            VerificationType.BRN -> prepareAutofillBrnRequest(verificationRequest)
        }
    }

    fun prepareNidPersonRequest(verificationRequest: VerificationRequest): NidPersonRequest {
        val dateOfBirth = verificationRequest.dateOfBirth.toString()
        val nid = verificationRequest.nid?.nid!!
        val fullname = verificationRequest.fullname

        return NidPersonRequest(dateOfBirth, nid, fullname)
    }

    fun prepareNidPersonPhotoRequest(verificationRequest: VerificationRequest): NidPersonPhotoRequest {
        val nid = verificationRequest.nid?.nid ?: throw IllegalArgumentException("NID NOT FOUND") // TODO
        val dateOfBirth = verificationRequest.dateOfBirth.toString()
        val fullname = verificationRequest.fullname

        return NidPersonPhotoRequest(dateOfBirth, nid, fullname)
    }

    fun prepareAutofillBrnRequest(verificationRequest: VerificationRequest): AutofillBrnRequest {
        val birthRegistrationNumber = verificationRequest.brn?.birthRegistrationNumber ?: throw IllegalArgumentException("BRN NOT FOUND") // TODO
        val dateOfBirth = verificationRequest.dateOfBirth.toString()

        return AutofillBrnRequest(birthRegistrationNumber,  dateOfBirth)
    }

    private enum class ErrorCodes(val code: Int) {
        INVALID_NID_NUMBER(10001),
        DOB_MATCH_FAILURE(10002),
        NAME_MATCH_FAILURE(10003),
        SYSTEM_ERROR(11000),
        LOCKED_USER(11001);

        companion object {
            fun getErrorStatus(code: Int): ErrorCodes {
                values().forEach {
                    if (code == it.code) return it
                }
                return SYSTEM_ERROR
            }
        }
    }
}