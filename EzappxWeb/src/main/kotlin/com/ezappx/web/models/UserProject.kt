package com.ezappx.web.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class UserProject(
        val username: String? = null,
        val projectId: String? = null,
        val mobileBuilderConfig: MobileBuilderConfig? = null,
        val createdAt: LocalDateTime? = null,
        @Id val id: String? = null
)