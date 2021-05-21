package com.haatehaate.login

import com.haatehaate.login.dto.LoginRequest
import com.haatehaate.login.dto.LoginResponse
import com.haatehaate.otp.SmsService
import com.haatehaate.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class LoginController(
    private val userService: UserService
) {
    @ResponseBody
    @GetMapping("/hello")
    fun hello(): String {
        return "Hello World"
    }

    @PostMapping("/login")
    fun login(@RequestBody @Valid loginRequest: LoginRequest): ResponseEntity<Any> {
        val user = userService.loginUser(loginRequest)
        val loginResponse = LoginResponse(success = LoginResponse.Success(user.username, ""))

        return ResponseEntity.ok(loginResponse)
    }
}