package com.ezappx.web.models

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

data class MobileAppProject(
        val username: String,
        val projectName: String,    //同一用户名称不可重复，不同用户名称可重复
        var createdAt: String,
        var updatedAt: String,
        var mobileOS: String,
        var binaryFiles: List<String>,
        var cordovaPlugins: List<String>    // cordova提供的插件
)

@Document
data class MobileAppProjectFile(
        val username: String,
        val projectName: String,
        val mobileOS: String,
        val filePath: String,
        val content: String,
        @JsonIgnore @Id var id: String? = null
)