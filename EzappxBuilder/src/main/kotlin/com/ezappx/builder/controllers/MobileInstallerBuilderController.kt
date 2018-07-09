package com.ezappx.builder.controllers

import com.ezappx.builder.models.AvailableMobileOS
import com.ezappx.builder.models.MobileInstallerBuilderConfig
import com.ezappx.builder.services.androidInstallerBuilder
import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("/api/v1")
class MobileInstallerBuilderController {
    private val log = LogFactory.getLog(MobileInstallerBuilderController::class.java)

    @ApiOperation(value = "获取可用移动操作系统平台", notes = "可用平台取决于部署环境")
    @RequestMapping("/available", method = [RequestMethod.GET])
    fun availableMobileOS() = listOf("Android", "iOS")

    @ApiOperation(value = "编译Android安装包", notes = "使用Cordova生成移动应用安装包")
    @ApiImplicitParam(name = "JSON配置", value = "JSON格式")
    @RequestMapping("/android/build-installer", method = [RequestMethod.POST])
    fun buildAndroidMobileInstaller(@RequestBody @ApiParam(name = "打包配置", value = "接受格式为JSON", required = true) builderConfig: MobileInstallerBuilderConfig): TmpResponse {
        log.debug("build android installer ...")
        log.debug(builderConfig)

        val builder = androidInstallerBuilder {
            projectId = builderConfig.uuid
            mobileOS = AvailableMobileOS.valueOf(builderConfig.mobileOS.toUpperCase())
        }
        return TmpResponse("init cordova project: ${builder.projectId}")
    }

    data class TmpResponse(val status: String)
}