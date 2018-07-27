package com.ezappx.web.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "ezappx.builder")
class MobileAppBuilderAPI {
    lateinit var base: String
    lateinit var androidBuilder: String
    lateinit var ios: String
}