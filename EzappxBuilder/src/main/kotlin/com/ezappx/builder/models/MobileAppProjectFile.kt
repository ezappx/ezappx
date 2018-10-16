package com.ezappx.builder.models

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * WWW资源文件类
 * @property username 用户名
 * @property projectName 工程名
 * @property mobileOS 移动应用系统平台
 * @property filePath 文件原始路径
 * @property content 文件内容
 * @property id 数据库ID
 */
@Document
data class MobileAppProjectFile(
        val username: String,
        val projectName: String,
        val mobileOS: String,
        val filePath: String,
        val content: String,
        @JsonIgnore @Id var id: String? = null
)