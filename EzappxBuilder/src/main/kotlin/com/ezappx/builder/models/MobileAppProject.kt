package com.ezappx.builder.models

import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.annotations.ApiModel
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@ApiModel(value = "打包配置", description = "工程资源和相关配置")
@Document
data class MobileAppProject(
        val username: String,
        val projectName: String,
        var createdAt: String,
        var updatedAt: String,
        var mobileProjectContent: MobileProjectContent?,
        @JsonIgnore
        @Id val id: String? = null
)

data class MobileProjectContent(
        var projectName: String, //同一用户名称不可重复，不同用户名称可重复
        var mobileOS: String,
        var projectFiles: List<ProjectFile>,
        var binaryFiles: List<String> //TODO 二进制文件以base64编码，或者BSON？或者二进制文件预先存储到db？
)

data class ProjectFile(val filePath: String, val content: String)
