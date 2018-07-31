package com.ezappx.web.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "ezappx.builder")
class BuilderProperties {
    lateinit var builderApi: String
    lateinit var androidBuilderApi: String
    lateinit var iosBuilderApi: String
}