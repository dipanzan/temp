package com.haatehaate.service

import com.haatehaate.nid.request.BasicNidVerificationRequest
import com.haatehaate.nid.PorichoyGovBdApi
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class NidService(
    private val porichoyGovBdApi: PorichoyGovBdApi,
    private val webClientBuilder: WebClient.Builder
) {
    private val webClient = webClientBuilder
        .baseUrl(porichoyGovBdApi.base_url)
        .defaultHeader(PorichoyGovBdApi.X_API_KEY, porichoyGovBdApi.api_key)
        .build()

    fun prepareBasicNidVerificationRequest(dateOfBirth: String, nid: String, fullname: String): Mono<String> {
        val basicNidVerificationRequest = BasicNidVerificationRequest(dateOfBirth, nid, fullname)

        return webClient.post().uri {
            it.path(PorichoyGovBdApi.TEST_NID_PERSON).build()
        }
            .bodyValue(basicNidVerificationRequest)
            .retrieve()
            .bodyToMono(String::class.java)
    }
}