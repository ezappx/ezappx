package com.ezappx.builder.models

import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.annotations.ApiModel
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * 移动应用工程
 * @property username 用户名
 * @property projectName 工程名
 * @property createdAt 创建时间
 * @property updatedAt 更新时间
 * @property mobileOS 移动应用系统平台
 * @property binaryFiles WWW资源文件的数据库ID
 * @property cordovaPlugins Cordova插件名称
 * @property id 数据库ID
 */
@ApiModel(value = "移动应用工程", description = "移动应用工程相关数据")
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