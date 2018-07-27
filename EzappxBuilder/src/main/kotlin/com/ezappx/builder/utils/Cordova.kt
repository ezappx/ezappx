package com.ezappx.builder.utils

object Cordova {
    private const val CORDOVA = "cordova"

    fun create(dirName: String, packageName: String, projectName: String) = listOf(CORDOVA, "create", dirName, packageName, projectName)

    fun addPlatform(mobileOS: String) = listOf(CORDOVA, "platform", "add", mobileOS.toLowerCase())

    fun addPlugin(pluginName: String) = listOf(CORDOVA, "plugin", "add", pluginName)

    fun build(mobileOS: String? = null) = when (mobileOS?.toLowerCase()) {
        null -> listOf(CORDOVA, "build")
        else -> listOf(CORDOVA, "build", mobileOS.toLowerCase())
    }
}