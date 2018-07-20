package com.ezappx.web.models

import java.time.LocalDateTime

data class MobileAppProject(
        val username: String,
        val projectId: String,
        var mobileAppBuilderConfig: MobileAppBuilderConfig?,
        var createdAt: String = LocalDateTime.now().toString()
)