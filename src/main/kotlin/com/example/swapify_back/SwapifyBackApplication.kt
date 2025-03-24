package com.example.swapify_back

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class SwapifyBackApplication

fun main(args: Array<String>) {
	runApplication<SwapifyBackApplication>(*args)
}
