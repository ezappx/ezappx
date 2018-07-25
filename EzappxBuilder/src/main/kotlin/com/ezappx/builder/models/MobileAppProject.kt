package com.ezappx.builder.models

import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.annotations.ApiModel
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@ApiModel(value = "打包配置", description = "工程资源和相关配置")
@Document
data class MobileAppProject(
        val username: String,
        val projectName: String,//同一用户名称不可重复，不同用户名称可重复
        val createdAt: String,
        var updatedAt: String,
        val mobileOS: String,
        val binaryFiles: List<String>,
        val cordovaPlugins: List<String>, // cordova提供的插件
        @JsonIgnore
        @Id var id: String? = null
)