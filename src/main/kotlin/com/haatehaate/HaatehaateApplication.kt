package com.haatehaate

import com.haatehaate.nid.PorichoyGovBdApi
import com.haatehaate.otp.OnnorokomSmsApi
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(OnnorokomSmsApi::class, PorichoyGovBdApi::class)
open class HaatehaateApplication

fun main(args: Array<String>) {
	runApplication<HaatehaateApplication>(*args)
}
