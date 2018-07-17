package com.ezappx.web.repositories

import com.ezappx.web.models.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserProjectTests(
        @Autowired private val userProjectRepository: UserProjectRepository
) {

    @Test
    fun `Assert user-project to db`() {
        val mobileBuilderConfig = MobileBuilderConfig("test-uuid", "ANDROID",
                listOf(CustomHTMLFiles("index.html", "html content")),
                listOf(CustomCSSFiles("css.css", "css content")),
                DependentFiles(listOf("dependent-css.css"), listOf("dependent-js.js"))
        )
        val userProject = UserProject("test-user", mobileBuilderConfig.uuid, mobileBuilderConfig, LocalDateTime.now())
        userProjectRepository.save(userProject)

        val project = userProjectRepository.findByProjectId("test-uuid").firstOrNull()
        assertThat(project).isNotNull
    }
}