package com.haatehaate.config

import com.haatehaate.nid.api.PorichoyGovBdApi
import com.haatehaate.otp.SmsApi
import io.netty.channel.ChannelOption
import io.netty.handler.ssl.SslContext
import io.netty.handler.ssl.SslContextBuilder
import io.netty.handler.ssl.util.InsecureTrustManagerFactory
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient


@Configuration
class WebClientConfig(
    private val porichoyGovBdApi: PorichoyGovBdApi,
    private val smsApi: SmsApi,
    private val webClientBuilder: WebClient.Builder
) {
    companion object {
        const val NID_WEB_CLIENT = "NID_WEB_CLIENT"
        const val SMS_WEB_CLIENT = "SMS_WEB_CLIENT"
        private const val CONNECT_TIMEOUT_MILLIS_10_000 = 10_000
        private const val TIMEOUT_SECONDS = 10
    }

    init {
        //webClientBuilder.filter(ExchangeFilterFunction.ofResponseProcessor(::handleExternalApiErrors))
    }

    /*private fun handleExternalApiErrors(clientResponse: ClientResponse): Mono<ClientResponse> {
        return if (clientResponse.statusCode().isError) {
            clientResponse.bodyToMono(ApiError::class.java)
                .flatMap { apiErrorResponse ->
                    Mono.error(VerificationException(apiErrorResponse.message!!))
                }
        } else Mono.just(clientResponse)
    }*/

    @Bean(NID_WEB_CLIENT)
    fun provideNidWebClient(): WebClient {
        val sslContext = getSslContext()
        val httpClient = getHttpClient(sslContext)

        return webClientBuilder
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .defaultHeader(PorichoyGovBdApi.X_API_KEY_HEADER, porichoyGovBdApi.api_key)
            .baseUrl(porichoyGovBdApi.base_url)
            .build()
    }

    @Bean(SMS_WEB_CLIENT)
    fun provideSmsWebClient(): WebClient {
        val sslContext = getSslContext()
        val httpClient = getHttpClient(sslContext)

        return webClientBuilder
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .baseUrl(smsApi.base_url)
            .build()
    }

    // TODO: Don't use this in production!
    fun getSslContext(): SslContext {
        return SslContextBuilder.forClient()
            .trustManager(InsecureTrustManagerFactory.INSTANCE)
            .build()
    }

    private fun getHttpClient(sslContext: SslContext): HttpClient {
        return HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECT_TIMEOUT_MILLIS_10_000)
            .secure {
                it.sslContext(sslContext)
            }
            .doOnConnected {
                it.apply {
                    addHandlerLast(ReadTimeoutHandler(TIMEOUT_SECONDS))
                    addHandlerLast(WriteTimeoutHandler(TIMEOUT_SECONDS))
                }
            }
    }
}