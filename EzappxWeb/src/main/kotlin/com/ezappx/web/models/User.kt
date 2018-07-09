package com.ezappx.web.models

import org.springframework.data.annotation.Id

data class User(
        val username: String,
        val email: String,
        val password: String,
        @Id var id: String? = null
)