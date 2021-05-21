package com.haatehaate.nid.controller

import com.haatehaate.nid.request.VerificationRequest
import com.haatehaate.nid.service.NidService
import com.haatehaate.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/user/verification")
class VerificationController(
    private val userService: UserService,
    private val nidService: NidService
) {
    @PostMapping("/nid-brn")
    fun verifyUser(@RequestBody @Valid verificationRequest: VerificationRequest) {




        val response = nidService.processVerificationRequest(verificationRequest)



    }
}