package com.jissay.unlocked

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UnlockedServerApplication

fun main(args: Array<String>) {
	runApplication<UnlockedServerApplication>(*args)
}
