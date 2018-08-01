package com.ezappx.web.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "ezappx.file")
class FileStorageProperties {
    lateinit var uploadDir: String
    lateinit var uploadApi: String
    lateinit var resourceApi: String
}