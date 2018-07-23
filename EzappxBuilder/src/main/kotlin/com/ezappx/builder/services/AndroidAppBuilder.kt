package com.ezappx.builder.services

import com.ezappx.builder.utils.Cordova
import java.nio.file.Paths

class AndroidAppBuilder : AbstractMobileAppBuilder() {

    override fun initProject() {
        processUtil.execInDir = userProjectDir.toFile()
        info("init ezappx-cordova project")
        processUtil.exec(Cordova.create(project.projectName, projectPackage, project.projectName))
    }

    override fun addPlatform() {
        debug("add platform")
        processUtil.exec(Cordova.addPlatform(project.mobileOS))
    }

    override fun addCordovaPlugins() {
        for (plugin in project.cordovaPlugins) {
            processUtil.exec(Cordova.addPlugin(plugin))
        }
    }

    override fun addCodeResources() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun build() {
        debug("build app project")
        processUtil.exec(Cordova.build(project.mobileOS))
    }

    override fun installerFile() = Paths.get(userProjectDir.toString(), "platforms", "android", "build", "outputs", "apk", "android-debug.apk")!!
}

fun androidBuilder(init: AndroidAppBuilder.() -> Unit): AndroidAppBuilder {
    val androidAppBuilder = AndroidAppBuilder()
    androidAppBuilder.init()
    androidAppBuilder.initBuilderEnv()
    androidAppBuilder.initProject()
    androidAppBuilder.addPlatform()
    androidAppBuilder.addCordovaPlugins()
    androidAppBuilder.build()
    return androidAppBuilder
}