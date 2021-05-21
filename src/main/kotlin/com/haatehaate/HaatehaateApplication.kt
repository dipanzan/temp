package com.haatehaate

import com.haatehaate.nid.api.PorichoyGovBdApi
import com.haatehaate.otp.SmsApi
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(SmsApi::class, PorichoyGovBdApi::class)
class HaatehaateApplication

fun main(args: Array<String>) {
	runApplication<HaatehaateApplication>(*args)
}
