package com.haatehaate.controller

import com.haatehaate.nid.request.VerificationRequest
import com.haatehaate.service.NidService
import com.haatehaate.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/user/verification")
class UserVerificationController(
    private val userService: UserService,
    private val nidService: NidService
) {


    @PostMapping("/nid-brn")
    fun verifyUser(@RequestBody @Valid verificationRequest: VerificationRequest): ResponseEntity<Any> {
        nidService.processVerificationRequest(verificationRequest)

        return ResponseEntity.ok().body("OK")
    }
}