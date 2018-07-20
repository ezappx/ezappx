package com.ezappx.web.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "ezappx.builder")
class MobileAppBuilderAPI {
    lateinit var android: String
    lateinit var ios: String
}