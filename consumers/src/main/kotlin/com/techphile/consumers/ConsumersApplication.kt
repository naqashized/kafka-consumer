package com.techphile.consumers

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ConsumersApplication

fun main(args: Array<String>) {
	runApplication<ConsumersApplication>(*args)
}
