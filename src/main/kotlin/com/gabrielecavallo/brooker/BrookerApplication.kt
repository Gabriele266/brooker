package com.gabrielecavallo.brooker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BrookerApplication

fun main(args: Array<String>) {
	runApplication<BrookerApplication>(*args)
}
