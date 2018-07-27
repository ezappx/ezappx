package com.ezappx.builder.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import java.nio.file.Paths

@Component
@ConfigurationProperties(prefix = "ezappx.builder")
class MobileAppBuilderProperty {
    lateinit var userProjectDirName: String
    val baseDir = Paths.get(System.getProperty("user.dir"))!!
}