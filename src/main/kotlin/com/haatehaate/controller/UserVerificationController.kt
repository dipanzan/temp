package com.haatehaate.controller

import com.haatehaate.nid.request.NidVerificationRequest
import com.haatehaate.nid.request.UserVerificationRequest
import com.haatehaate.response.PorichoyGovBdApiResponse
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

    fun verifyUserNid(@RequestBody @Valid nidVerificationRequest: NidVerificationRequest) {
        System.err.println("Fullname: ${nidVerificationRequest.fullname}, NID ${nidVerificationRequest.nid}")
        nidService.prepareBasicNidVerificationRequest(nidVerificationRequest.dateOfBirth.toString(), nidVerificationRequest.nid, nidVerificationRequest.fullname)
    }

    @PostMapping("/nid-brn")
    fun verifyUser(@RequestBody @Valid userVerificationRequest: UserVerificationRequest): ResponseEntity<Any> {




        val nid = userVerificationRequest.nid?.nid
        val fullname = userVerificationRequest.fullname
        val dateOfBirth = userVerificationRequest.dateOfBirth.toString()

        val verificationType = userVerificationRequest.verificationType

        System.err.println("hello = $verificationType")

        val test123 = nidService.prepareBasicNidVerificationRequest(dateOfBirth, nid!!, fullname).block()

        System.err.println("PORICHOY: $test123")

        return ResponseEntity.ok().body(test123)
    }
}