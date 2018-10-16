package com.ezappx.builder.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import java.nio.file.Paths

/**
 * EzappxBuilder的属性参数
 */
@Component
@ConfigurationProperties(prefix = "ezappx.builder")
class MobileAppBuilderProperty {
    /**
     * 所有用户工程的父目录
     */
    lateinit var userProjectDirName: String
    val baseDir = Paths.get(System.getProperty("user.dir"))!!
}