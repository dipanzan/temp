package com.haatehaate

import com.haatehaate.api.nid.NidApi
import com.haatehaate.api.sms.SmsApi
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication


@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@EnableConfigurationProperties(SmsApi::class, NidApi::class)
class HaatehaateApplication

fun main(args: Array<String>) {
    runApplication<HaatehaateApplication>(*args)
}