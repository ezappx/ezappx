package com.ezappx.builder.services.utils

import java.nio.file.Paths

object MobileAppBuilderUtil {
    fun androidAppUri(projectDir: String, username: String, projectName: String) = Paths.get(projectDir, username, projectName, "platforms", "android", "build", "outputs", "apk", "android-debug.apk")!!
}