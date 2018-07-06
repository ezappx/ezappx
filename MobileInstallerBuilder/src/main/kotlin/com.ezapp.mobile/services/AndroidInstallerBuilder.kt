package com.ezapp.mobile.services

import com.ezapp.mobile.services.utils.ProcessUtils

class AndroidInstallerBuilder : AbstractMobileInstallerBuilder() {

    override fun initProject() {
        log.info("init cordova project at $BASE_DIR ...")
        val processBuilder = ProcessBuilder(CORDOVA, "create", projectId, DEFAULT_PACKAGE, projectId)
        processBuilder.inheritIO()
        processBuilder.directory(BASE_DIR.toFile())
        val process = processBuilder.start()
        ProcessUtils.readProcessOutput(process)
        process.waitFor()
        log.info("initialization finished")
    }

    override fun addPlatform() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addCordovaPlugins() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addCodeResources() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun build() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun installerUrl() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

fun androidInstallerBuilder(init: AndroidInstallerBuilder.() -> Unit): AndroidInstallerBuilder {
    val aib = AndroidInstallerBuilder()
    aib.init()
    aib.initProject()
    return aib
}