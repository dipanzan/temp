package com.haatehaate.nid.service

import com.haatehaate.config.WebClientConfig
import com.haatehaate.nid.api.PorichoyGovBdApi
import com.haatehaate.nid.api.request.AutofillBrnRequest
import com.haatehaate.nid.api.request.NidPersonPhotoRequest
import com.haatehaate.nid.api.request.NidPersonRequest
import com.haatehaate.nid.api.request.PorichoyApiRequest
import com.haatehaate.nid.api.response.AutofillBrnResponse
import com.haatehaate.nid.api.response.NidPersonPhotoResponse
import com.haatehaate.nid.api.response.NidPersonResponse
import com.haatehaate.nid.api.response.PorichoyApiResponse
import com.haatehaate.nid.dto.VerificationResponse
import com.haatehaate.nid.exceptions.VerificationException
import com.haatehaate.nid.request.VerificationRequest
import com.haatehaate.service.UserService
import com.haatehaate.utils.validator.logger
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class NidService(
    @Qualifier(WebClientConfig.NID_WEB_CLIENT)
    private val webClient: WebClient,
    private val porichoyGovBdApi: PorichoyGovBdApi,
    private val userService: UserService
) {
    companion object {
        private val log = logger()
    }

    fun processVerificationRequest(verificationRequest: VerificationRequest): VerificationResponse {
        val body = porichoyGovBdApi.prepareApiRequest(verificationRequest)
        val response = nidPersonRequest(body).block()


        TODO()
    }

    private fun parseApiResponse(porichoyApiResponse: PorichoyApiResponse): Unit {
        when (porichoyApiResponse) {
            is NidPersonResponse -> TODO()
            is NidPersonPhotoResponse -> TODO()
            is AutofillBrnResponse -> TODO()
        }
    }



    private fun nidPersonRequest(nidPersonRequest: PorichoyApiRequest): Mono<NidPersonResponse> {
        return webClient.post().uri {
            it.path(PorichoyGovBdApi.TEST_NID_PERSON_URL).build()
        }
            .bodyValue(nidPersonRequest)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .onStatus(HttpStatus::isError) { clientResponse ->
                clientResponse.bodyToMono((String::class.java)).flatMap {
                    Mono.error(VerificationException(it))
                }
            }
            .bodyToMono(NidPersonResponse::class.java)

    }

    private fun nidPersonPhotoRequest(nidPersonPhotoRequest: NidPersonPhotoRequest): Mono<NidPersonPhotoResponse> {
        return webClient.post().uri {
            it.path(PorichoyGovBdApi.NID_PERSON_PHOTO_URL).build()
        }
            .bodyValue(nidPersonPhotoRequest)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .onStatus(HttpStatus::isError) { clientResponse ->
                clientResponse.bodyToMono((String::class.java))
                    .flatMap {
                        Mono.error(VerificationException(it))
                    }
            }
            .bodyToMono(NidPersonPhotoResponse::class.java)
    }

    private fun autofillBrnRequest(autofillBrnRequest: AutofillBrnRequest): Mono<AutofillBrnResponse> {
        return webClient.post().uri {
            it.path(PorichoyGovBdApi.VERIFY_BIRTH_REGISTRATION_URL).build()
        }
            .bodyValue(autofillBrnRequest)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .onStatus(HttpStatus::isError) { clientResponse ->
                clientResponse.bodyToMono((String::class.java)).flatMap {
                    Mono.error(VerificationException(it))
                }
            }
            .bodyToMono(AutofillBrnResponse::class.java)
    }
}