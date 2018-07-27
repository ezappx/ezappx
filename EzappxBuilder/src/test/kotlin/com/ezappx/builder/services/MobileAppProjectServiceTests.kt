package com.ezappx.builder.services

import com.ezappx.builder.models.MobileAppProject
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MobileAppProjectServiceTests(@Autowired private val mobileAppProjectService: MobileAppProjectService) {

    @Test
    fun `query mobile app project project file`() {
        val filesId = listOf("5b57eb5cdd2e8928f8bad3b0", "5b57eb5ddd2e8928f8bad3b1")
        val mobileAppProject = MobileAppProject("test", "test", "time", "time","android",filesId, listOf())
        mobileAppProjectService.prepareMobileAppProjectDir(mobileAppProject)
    }
}