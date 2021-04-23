package com.haatehaate.controller

import com.haatehaate.login.LoginRequest
import com.haatehaate.login.LoginResponse
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