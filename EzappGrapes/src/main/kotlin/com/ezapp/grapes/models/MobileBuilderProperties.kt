package com.ezapp.grapes.models

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "com.ezapp.mobile.builder")
class MobileBuilderProperties {
    lateinit var android: String
    lateinit var ios: String
}