package com.ezappx.web.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(
        val username: String? = null,
        val email: String? = null,
        val password: String? = null,
        @Id var id: String? = null
)