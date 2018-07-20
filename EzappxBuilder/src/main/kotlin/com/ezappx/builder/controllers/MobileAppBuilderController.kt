package com.ezappx.builder.controllers

import com.ezappx.builder.models.BuilderResponse
import com.ezappx.builder.models.MobileAppProject
import com.ezappx.builder.services.MobileAppBuilderService
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.apache.commons.logging.LogFactory
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("/api/v1")
class MobileAppBuilderController(private val mobileAppBuilderService: MobileAppBuilderService) {
    private val log = LogFactory.getLog(MobileAppBuilderController::class.java)

    @ApiOperation(value = "获取可用移动操作系统平台", notes = "可用平台取决于部署环境")
    @RequestMapping("/available", method = [RequestMethod.GET])
    fun availableMobileOS() = listOf("Android", "iOS")

    @ApiOperation(value = "编译Android安装包", notes = "生成移动应用安装包")
    @ApiImplicitParam(name = "JSON文件", value = "移动应用工程")
    @RequestMapping("/android/build", method = [RequestMethod.POST])
    fun buildAndroidMobileInstaller(@RequestBody @ApiParam(name = "资源与配置", value = "JSON格式", required = true) mobileAppProject: MobileAppProject): BuilderResponse {
        log.debug(mobileAppProject)
        mobileAppBuilderService.saveMobileAppProject2DB(mobileAppProject)
//        return try {
//            val builder = androidInstallerBuilder {
//                projectId = mobileAppProject.uuid
//                mobileOS = AvailableMobileOS.valueOf(mobileAppProject.mobileOS.toUpperCase())
//            }
//            BuilderResponse("init cordova project: ${builder.projectId}")
//        } catch (e: IOException) {
//            log.error(e)
//            BuilderResponse("can not init cordova project: ${mobileAppProject.uuid}")
//        }
        return BuilderResponse("init cordova project: ${mobileAppProject.projectName}")
    }
}