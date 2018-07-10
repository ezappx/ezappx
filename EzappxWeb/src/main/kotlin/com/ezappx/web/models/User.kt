package com.ezappx.web.models

import org.springframework.data.annotation.Id

data class User(
        val username: String? = null,
        val email: String? = null,
        val password: String? = null,
        @Id var id: String? = null
)