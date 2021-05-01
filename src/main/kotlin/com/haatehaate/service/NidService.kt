package com.haatehaate.service

import com.haatehaate.nid.PorichoyGovBdApi
import com.haatehaate.nid.request.BasicNidRequest
import com.haatehaate.nid.request.VerificationRequest
import com.haatehaate.utils.validator.logger
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class NidService(
    porichoyGovBdApi: PorichoyGovBdApi,
    webClientBuilder: WebClient.Builder
) {
    private val webClient = webClientBuilder
        .baseUrl(porichoyGovBdApi.base_url)
        .defaultHeader(PorichoyGovBdApi.X_API_KEY_HEADER, porichoyGovBdApi.api_key)
        .build()

    companion object {
        private val log = logger()
    }

    fun processVerificationRequest(verificationRequest: VerificationRequest) {
        val body = prepareBasicNidRequest(verificationRequest)

        val response = prepareWebClientWithUrlAndBody(PorichoyGovBdApi.TEST_NID_PERSON_URL, body).block()

        log.debug("PORICHOY RESPONSE: $response")
    }

    private fun prepareBasicNidRequest(verificationRequest: VerificationRequest): BasicNidRequest {
        val dateOfBirth = verificationRequest.dateOfBirth.toString()
        val nid = verificationRequest.nid?.nid!!
        val fullname = verificationRequest.fullname

        return BasicNidRequest(dateOfBirth, nid, fullname)
    }

    private fun prepareWebClientWithUrlAndBody(url: String, body: Any): Mono<String> {
        return webClient.post().uri {
            it.path(url).build()
        }
            .bodyValue(body)
            .retrieve()
            .bodyToMono(String::class.java)
    }

    private fun parseApiResponse(response: String) {

    }
}