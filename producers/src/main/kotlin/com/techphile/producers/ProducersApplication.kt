package com.techphile.producers

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ProducersApplication

fun main(args: Array<String>) {
	runApplication<ProducersApplication>(*args)
}

