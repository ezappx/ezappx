package com.ezappx.builder.utils

import java.nio.file.Files

/**
 * 编译Android应用
 */
class IOSAppBuilder : AbstractMobileAppBuilder() {

    /**
     * 初始化Cordova工程
     */
    override fun initProject() {
        if (!Files.exists(projectDir)) {
            info("init ezappx-cordova project at ${projectDir.toAbsolutePath()}")
            ProcessUtils.exec(userProjectDir, Cordova.create(project.projectName, projectPackage, project.projectName))
        }
    }

    /**
     * 添加ios平台
     */
    override fun addPlatform() {
        // 添加iOS编译环境
        if (!Files.exists(projectDir.resolve("platforms").resolve("ios"))) {
            debug("add ios platform")
            ProcessUtils.exec(projectDir, Cordova.addPlatform("ios", version = "6.4.0")) //FIXME iOS插件版本
        }
    }

    /**
     * 添加Cordova插件
     */
    override fun addCordovaPlugins() {
        debug("add cordova plugins")
        for (plugin in project.cordovaPlugins) {
            ProcessUtils.exec(projectDir, Cordova.addPlugin(plugin))
        }
    }

    /**
     * 编译
     */
    override fun build() {
        debug("build app project")
        appInstaller = ProcessUtils.exec(projectDir, Cordova.build(project.mobileOS)).trim()
    }
}

/**
 * Type-Safe构建函数，用于调用[iosAppBuilder]
 * @param init 初始化[iosAppBuilder]中的参数
 */
fun iosAppBuilder(init: IOSAppBuilder.() -> Unit): IOSAppBuilder {
    val builder = IOSAppBuilder()
    builder.init()
    builder.initBuilderArgs()
    builder.initProject()
    builder.addResources()
    builder.addPlatform()
    builder.addCordovaPlugins()
    builder.build()
    return builder
}