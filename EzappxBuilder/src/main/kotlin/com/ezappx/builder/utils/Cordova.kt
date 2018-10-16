package com.ezappx.builder.utils

/**
 * Cordova命令行封装
 */
object Cordova {
    private const val CORDOVA = "cordova"

    /**
     * 在[dirName]中创建Cordova工程
     * @param dirName 工程目录
     * @param packageName 移动应用包名
     * @param projectName 工程名称
     */
    fun create(dirName: String, packageName: String, projectName: String) = listOf(CORDOVA, "create", dirName, packageName, projectName)

    /**
     * 添加移动应用平台到Cordova工程
     * @param mobileOS 移动操作系统
     * @param version 移动操作系统版本
     */
    fun addPlatform(mobileOS: String, version:String) = listOf(CORDOVA, "platform", "add", "${mobileOS.toLowerCase()}@$version")

    /**
     * 添加Cordova插件
     * @param pluginName 插件名称，详细见 https://cordova.apache.org/plugins/
     */
    fun addPlugin(pluginName: String) = listOf(CORDOVA, "plugin", "add", pluginName)

    /**
     * 编译移动应用
     * @param mobileOS 目标平台
     */
    fun build(mobileOS: String? = null) = when (mobileOS?.toLowerCase()) {
        null -> listOf(CORDOVA, "build")
        else -> listOf(CORDOVA, "build", mobileOS.toLowerCase())
    }
}