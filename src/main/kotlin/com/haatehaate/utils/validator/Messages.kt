package com.haatehaate.utils.validator

object Messages {
    const val VALID_PHONE = "Phone number must consist of atleast 11 digits, starting with +880"
    const val VALID_PASSWORD = "Password should contain atleast [a-zA-Z0-9] and special characters of min length 8"
    const val UNEXPECTED_ERROR = "Unexpected error"
    const val REQUEST_BODY_INVALID = "Request body invalid"
    const val PROVIDE_VALID_CREDENTIALS = "Please provide both phone number and password"
    const val PROVIDE_VALID_USER_VERIFICATION_DATA = "Please provide valid user verifiable data"


    const val VALID_VERIFICATION_TYPE = "Verification type must be NID or BRN"
    const val VALID_FULLNAME = "Please provide fullname that is registered with your NID."
    const val VALID_BIRTH_REGISTRATION_NO = "Birth Registration Number should be 17 digits."
    const val VALID_NATIONAL_IDENTIFICATION_NO = "National Identification Number must be 10, 13 or 17 digits."

}