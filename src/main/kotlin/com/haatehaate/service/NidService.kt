package com.haatehaate.service

import com.haatehaate.api.nid.NidApi
import com.haatehaate.api.nid.dto.VerificationRequest
import com.haatehaate.api.nid.dto.VerificationResponse
import com.haatehaate.api.nid.dto.request.AutofillBrnRequest
import com.haatehaate.api.nid.dto.request.NidPersonPhotoRequest
import com.haatehaate.api.nid.dto.request.PorichoyApiRequest
import com.haatehaate.api.nid.dto.response.AutofillBrnResponse
import com.haatehaate.api.nid.dto.response.NidPersonPhotoResponse
import com.haatehaate.api.nid.dto.response.NidPersonResponse
import com.haatehaate.api.nid.dto.response.PorichoyApiResponse
import com.haatehaate.config.NidWebClient
import com.haatehaate.utils.error.VerificationException
import com.haatehaate.utils.validator.logger
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class NidService(
    @NidWebClient private val webClient: WebClient,
    private val nidApi: NidApi,
    private val userService: UserService
) {
    companion object {
        private val log = logger()
    }

    fun processVerificationRequest(verificationRequest: VerificationRequest): VerificationResponse {
        val body = nidApi.prepareApiRequest(verificationRequest)
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
            it.path(NidApi.TEST_NID_PERSON_URL).build()
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
            it.path(NidApi.NID_PERSON_PHOTO_URL).build()
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
            it.path(NidApi.VERIFY_BIRTH_REGISTRATION_URL).build()
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