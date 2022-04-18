package com.gabrielecavallo.brooker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class BrookerApplication

fun main(args: Array<String>) {
	runApplication<BrookerApplication>(*args)
}
