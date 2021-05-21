package com.haatehaate.utils.validator

object Messages {
    const val USERNAME_NOT_FOUND = "Username not found, please register an account."
    const val INCORRECT_USERNAME_OR_PASSWORD = "Incorrect username or password."

    const val VALID_PHONE = "Phone number must consist of atleast 11 digits, starting with +880"
    const val VALID_PASSWORD = "Password should contain atleast [a-zA-Z0-9] and special characters of min length 8"
    const val PASSWORDS_MATCH = "Both password and confirmed passwords must match"
    const val UNEXPECTED_ERROR = "Unexpected error"
    const val REQUEST_BODY_INVALID = "Request body invalid"
    const val PROVIDE_VALID_CREDENTIALS = "Please provide both phone number and password"
    const val PROVIDE_VALID_USER_VERIFICATION_DATA = "Please provide valid user verifiable data"


    const val FAILED_TO_SEND_OTP = "Failed to send OTP, please try again later"
    const val VALID_OTP = "OTP must be 5 digits."
    const val OTP_DID_NOT_MATCH = "User submitted OTP did not match, please try again or request new OTP."
    const val OTP_REQUESTED_TOO_MANY_TIMES = "User requested OTP too many times, please try again later with valid credentials"

    const val REGISTRATION_SUCCESS = "Successfully completed registration."
    const val PLEASE_CONFIRM_OTP = "Please confirm registration with OTP."
    const val REGISTRATION_FAILED = "Registration failed, please see reason for more details."

    const val VALID_VERIFICATION_TYPE = "Verification type must be NID or BRN"
    const val VALID_FULLNAME = "Please provide fullname that is registered with your NID."
    const val VALID_BIRTH_REGISTRATION_NO = "Birth Registration Number should be 17 digits."
    const val VALID_NATIONAL_IDENTIFICATION_NO = "National Identification Number must be 10, 13 or 17 digits."

    const val NID_VERIFICATION_SUCCESSFUL = "National Identification Number successfully verified."
    const val NID_VERIFICATION_FAILED = "National Identification Number verification failed."

    const val BRN_VERIFICATION_SUCCESSFUL = "Birth Registration Number verification successfully verified."
    const val BRN_VERIFICATION_FAILED = "Birth Registration Number verification failed."

}