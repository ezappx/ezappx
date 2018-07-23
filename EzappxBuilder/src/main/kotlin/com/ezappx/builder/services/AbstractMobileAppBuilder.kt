package com.ezappx.builder.services

import com.ezappx.builder.models.MobileAppProject
import com.ezappx.builder.utils.ProcessUtils
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

abstract class AbstractMobileAppBuilder {
    companion object {
        val log: Log = LogFactory.getLog(AbstractMobileAppBuilder::class.java)
        val BASE_DIR: Path = Paths.get(System.getProperty("user.dir"))
        const val BASE_PACKAGE = "com.ezappx"
    }

    protected val processUtil = ProcessUtils()
    lateinit var project: MobileAppProject
    lateinit var projectPackage: String
    lateinit var userProjectDir: Path

    /**
     * 若要自定义包名和工程目录名称
     * 默认初始化工程目录为 UserProjects
     */
    fun initBuilderEnv() {
        projectPackage = BASE_PACKAGE + "." + project.username + "." + project.projectName
        // 创建用户工作目录
        val baseDir = BASE_DIR.resolve("UserProjects")
        if (!Files.exists(baseDir)) {
            Files.createDirectory(baseDir)
        }
        userProjectDir = baseDir.resolve(project.username)
        if (!Files.exists(userProjectDir)) {
            Files.createDirectory(userProjectDir)
        }
        processUtil.execInDir = userProjectDir.toFile()
    }

    abstract fun initProject()
    abstract fun addPlatform()
    abstract fun addCordovaPlugins()
    abstract fun addCodeResources()
    abstract fun build()
    abstract fun installerFile(): Path

    protected fun debug(msg: String) {
        log.debug("[${project.username}/${project.projectName}] $msg")
    }

    protected fun info(msg: String) {
        log.info("[${project.username}/${project.projectName}] $msg")
    }
}