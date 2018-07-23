package com.ezappx.builder.services

import com.ezappx.builder.models.MobileAppProject
import org.junit.jupiter.api.Test
import java.time.LocalDateTime


class AppBuilderTests {
    @Test
    fun `android builder test`() {
        val builder = androidBuilder {
            project = MobileAppProject(
                    username = "ing",
                    projectName = "TestProject",
                    createdAt = LocalDateTime.now().toString(),
                    updatedAt = LocalDateTime.now().toString(),
                    mobileOS = "android",
                    binaryFiles = listOf("ing/TestProject-file1", "ing/TestProject-file2"),
                    cordovaPlugins = listOf("cordova-plugin1", "cordova-plugin2"))
        }
        println(builder.installerFile())
    }
}