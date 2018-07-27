package com.ezappx.builder.models

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class MobileAppProjectFile(
        val username: String,
        val projectName: String,
        val mobileOS: String,
        val filePath: String,
        val content: String,
        @JsonIgnore @Id var id: String? = null
)