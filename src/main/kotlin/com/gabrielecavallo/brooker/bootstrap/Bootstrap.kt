package com.gabrielecavallo.brooker.bootstrap

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class Bootstrap: CommandLineRunner {
    override fun run(vararg args: String?) {
        println("Brooker application started")
    }
}