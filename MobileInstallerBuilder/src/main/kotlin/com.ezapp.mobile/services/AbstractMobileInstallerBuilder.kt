package com.ezapp.mobile.services

import com.ezapp.mobile.models.AvailableMobileOS
import org.apache.commons.logging.LogFactory
import java.nio.file.Paths

abstract class AbstractMobileInstallerBuilder {
    companion object {
        val log = LogFactory.getLog(AbstractMobileInstallerBuilder::class.java)
        val CORDOVA = "cordova"
        val BASE_DIR = Paths.get(System.getProperty("user.dir"))

        val DEFAULT_PACKAGE = "com.ezapp.build"
    }

    var projectId: String? = null
    var mobileOS: AvailableMobileOS? = null

    abstract fun initProject()
    abstract fun addPlatform()
    abstract fun addCordovaPlugins()
    abstract fun addCodeResources()
    abstract fun build()
    abstract fun installerUrl()
}