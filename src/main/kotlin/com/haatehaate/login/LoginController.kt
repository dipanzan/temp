package com.haatehaate.login

import com.haatehaate.exception.InvalidLoginException
import com.haatehaate.login.dto.LoginRequest
import com.haatehaate.login.dto.LoginResponse
import com.haatehaate.utils.validator.Messages
import com.haatehaate.utils.validator.logger
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class LoginController(
    private val loginService: LoginService
) {
    companion object {
        private val log = logger()
    }

    @PostMapping("/login")
    fun login(@RequestBody @Valid loginRequest: LoginRequest): LoginResponse {
        val user = loginService.loginUser(loginRequest)

        return user.token?.let {
            LoginResponse(
                success = LoginResponse.Success(
                    username = user.username,
                    tokenId = it.value,
                    tokenValidTill = it.tokenValidTill
                )
            )
        } ?: throw InvalidLoginException(reason = Messages.UNEXPECTED_ERROR, path = "/user/login")
    }

    @PostMapping("/logout")
    fun logout() {

    }

}