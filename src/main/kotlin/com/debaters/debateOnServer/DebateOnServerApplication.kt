package com.debaters.debateOnServer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class DebateOnServerApplication

fun main(args: Array<String>) {
	runApplication<DebateOnServerApplication>(*args)
}
