package com.ezappx.builder.services

import com.ezappx.builder.models.MobileAppProject
import com.ezappx.builder.utils.ProcessUtils
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import java.nio.file.Path

abstract class AbstractMobileAppBuilder {
    companion object {
        val log: Log = LogFactory.getLog(AbstractMobileAppBuilder::class.java)
        const val BASE_PACKAGE = "com.ezappx"
        const val PREFIX = "ezappx"
    }

    lateinit var project: MobileAppProject
    lateinit var projectPackage: String
    lateinit var userProjectDir:Path
    lateinit var projectDir: Path
    lateinit var projectWWWDir: Path
    lateinit var addResources: () -> Unit

    /**
     * 设置包名、工作目录
     */
    fun initBuilderArgs() {
        // 添加PREFIX防止非法命名
        projectPackage = BASE_PACKAGE + "." + PREFIX + project.username + "." + PREFIX + project.projectName
        projectDir = userProjectDir.resolve(project.projectName)
        projectWWWDir = projectDir.resolve("www")
    }

    abstract fun initProject()
    abstract fun addPlatform()
    abstract fun addCordovaPlugins()
    abstract fun build()

    protected fun debug(msg: String) {
        log.debug("[${project.username} - ${project.projectName}] $msg")
    }

    protected fun info(msg: String) {
        log.info("[${project.username} - ${project.projectName}] $msg")
    }
}