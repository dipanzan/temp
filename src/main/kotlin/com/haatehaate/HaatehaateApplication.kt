package com.haatehaate

import com.haatehaate.otp.OnnorokomSmsApi
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(OnnorokomSmsApi::class)
open class HaatehaateApplication

fun main(args: Array<String>) {
	runApplication<HaatehaateApplication>(*args)
}
