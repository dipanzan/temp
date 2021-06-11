package com.haatehaate.config

import com.haatehaate.api.nid.NidApi
import com.haatehaate.api.sms.SmsApi
import io.netty.channel.ChannelOption
import io.netty.handler.ssl.SslContext
import io.netty.handler.ssl.SslContextBuilder
import io.netty.handler.ssl.util.InsecureTrustManagerFactory
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class SmsWebClient

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class NidWebClient

@Configuration
class WebClientConfig(
    private val nidApi: NidApi,
    private val smsApi: SmsApi,
    private val webClientBuilder: WebClient.Builder
) {
    companion object {
        private const val CONNECT_TIMEOUT_MILLIS_10_000 = 10_000
        private const val TIMEOUT_SECONDS = 10
    }

    @Bean
    @SmsWebClient
    fun provideSmsWebClient(): WebClient {
        val sslContext = getSslContext()
        val httpClient = getHttpClient(sslContext)

        return webClientBuilder
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .baseUrl(smsApi.base_url)
            .build()
    }

    @Bean
    @NidWebClient
    fun provideNidWebClient(): WebClient {
        val sslContext = getSslContext()
        val httpClient = getHttpClient(sslContext)

        return webClientBuilder
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .defaultHeader(NidApi.X_API_KEY_HEADER, nidApi.api_key)
            .baseUrl(nidApi.base_url)
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