package com.ezappx.web.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "ezappx.storage")
class DesignerStorageProperties {
    lateinit var storeApi: String
    lateinit var loadApi: String
}