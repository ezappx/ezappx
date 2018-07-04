package com.ezapp.grapes

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean

@SpringBootApplication
class WebDesignerApplication {
    @Bean
    fun restTemplate(builder: RestTemplateBuilder) = builder.build()
}

fun main(args: Array<String>) {
    runApplication<WebDesignerApplication>(*args)
}