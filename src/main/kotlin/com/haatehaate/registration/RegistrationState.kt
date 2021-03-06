package com.haatehaate.registration

sealed class RegistrationState {
    data class Success(val message: String) : RegistrationState(),
            

}
