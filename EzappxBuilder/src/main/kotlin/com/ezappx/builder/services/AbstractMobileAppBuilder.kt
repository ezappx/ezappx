package com.ezappx.builder.services

import com.ezappx.builder.models.MobileAppProject
import com.ezappx.builder.utils.ProcessUtils
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import java.nio.file.Path

/**
 * 编译移动应用的抽象类
 */
abstract class AbstractMobileAppBuilder {
    companion object {
        val log: Log = LogFactory.getLog(AbstractMobileAppBuilder::class.java)
        const val BASE_PACKAGE = "com.ezappx"
        const val PREFIX = "ezappx"
    }


    /**
     * 移动应用工程
     */
    lateinit var project: MobileAppProject
    /**
     * 用户工程目录
     */
    lateinit var userProjectDir: Path
    /**
     * 添加WWW资源到[projectWWWDir]
     */
    lateinit var addResources: () -> Unit
    /**
     * 移动应用包名
     */
    lateinit var projectPackage: String
    /**
     * Cordova工程目录，应位于[userProjectDir]下
     */
    lateinit var projectDir: Path
    /**
     * Cordova工程的WWW目录，应位于[projectDir]下
     */
    lateinit var projectWWWDir: Path //Cordova工程的WWW目录


    /**
     * 初始化包名、Cordova工程目录、Cordova工程WWW目录
     */
    fun initBuilderArgs() {
        // 添加PREFIX防止非法命名
        projectPackage = BASE_PACKAGE + "." + PREFIX + project.username + "." + PREFIX + project.projectName
        projectDir = userProjectDir.resolve(project.projectName)
        projectWWWDir = projectDir.resolve("www")
    }

    /**
     * 初始化Cordova工程
     */
    abstract fun initProject()

    /**
     * 添加移动应用平台
     */
    abstract fun addPlatform()

    /**
     * 添加Cordova插件
     */
    abstract fun addCordovaPlugins()

    /**
     * 编译移动应用
     */
    abstract fun build()

    /**
     * debug级别调试
     */
    protected fun debug(msg: String) {
        log.debug("[${project.username} - ${project.projectName}] $msg")
    }

    /**
     * info级别调试
     */
    protected fun info(msg: String) {
        log.info("[${project.username} - ${project.projectName}] $msg")
    }
}