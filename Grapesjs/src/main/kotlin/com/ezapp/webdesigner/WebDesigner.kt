package com.ezapp.webdesigner

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebDesignerApplication

fun main(args: Array<String>) {
    runApplication<WebDesignerApplication>(*args)
}