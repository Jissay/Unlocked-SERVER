package com.jissay.unlocked

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer

@SpringBootApplication
open class UnlockedServerApplication

fun main(args: Array<String>) {
	runApplication<UnlockedServerApplication>(*args)
}
