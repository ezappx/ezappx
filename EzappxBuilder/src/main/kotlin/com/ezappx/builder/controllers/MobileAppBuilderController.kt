package com.ezappx.builder.controllers

import com.ezappx.builder.models.MobileAppProject
import com.ezappx.builder.responses.MobileAppBuilderResponse
import com.ezappx.builder.services.MobileAppProjectService
import com.ezappx.builder.utils.AbstractMobileAppBuilder
import com.ezappx.builder.utils.androidBuilder
import com.ezappx.builder.utils.iosAppBuilder
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.IOException
import java.nio.file.Paths

/**
 * EzappxBuilder的REST接口
 */
@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("/api/v1")
class MobileAppBuilderController(@Autowired private val mobileAppProjectService: MobileAppProjectService) {
    private val log = LogFactory.getLog(MobileAppBuilderController::class.java)
    private var appBuilder: AbstractMobileAppBuilder? = null

    /**
     * 目前运行环境可编译的移动操作系统平台类型
     * @return 移动操作系统平台类型
     */
    @ApiOperation(value = "获取可用移动操作系统平台", notes = "可用平台取决于部署环境")
    @RequestMapping("/available", method = [RequestMethod.GET])
    fun availableMobileOS() = listOf("Android", "iOS") //TODO 根据目前操作系统自动适配

    /**
     * 编译Android应用接口
     * @param mobileAppProject 移动应用工程
     * @return [MobileAppBuilderResponse]
     */
    @ApiOperation(value = "编译Android安装包", notes = "编译生成移动应用安装包")
    @ApiImplicitParam(name = "mobileAppProject", value = "移动应用工程")
    @RequestMapping("/android/build", method = [RequestMethod.POST])
    fun buildAndroidMobileApp(@RequestBody @ApiParam(name = "移动应用工程", value = "JSON", required = true) mobileAppProject: MobileAppProject): MobileAppBuilderResponse {
        log.debug(mobileAppProject)
        mobileAppProjectService.saveMobileAppProject2DB(mobileAppProject)
        val userProjectDir = mobileAppProjectService.prepareMobileAppProjectDir(mobileAppProject)

        return try {
            appBuilder = androidBuilder {
                this.project = mobileAppProject
                this.userProjectDir = userProjectDir
                this.addResources = { mobileAppProjectService.createMobileAppProjectFiles(projectWWWDir, project) }
                // TODO 应该放在配置文件或前端项目设置里，测试用4.3版本
                this.androidMinSDK = "18"
            }
            log.debug("android app installer : ${appBuilder?.appInstaller}")
            MobileAppBuilderResponse(
                    status = "Start download ${appBuilder?.project?.username} - ${appBuilder?.project?.projectName} app installer ...",
                    downloadUrl = "/app/download/${mobileAppProject.username}/${mobileAppProject.projectName}")
        } catch (e: IOException) {
            log.error(e)
            MobileAppBuilderResponse("can not init cordova project")
        }
    }

    /**
     * 编译iOS应用接口
     * @param mobileAppProject 移动应用工程
     * @return [MobileAppBuilderResponse]
     */
    @ApiOperation(value = "编译ios安装包", notes = "编译生成ios应用安装包")
    @ApiImplicitParam(name = "mobileAppProject", value = "移动应用工程")
    @RequestMapping("ios/build", method = [RequestMethod.POST])
    fun buildIOSMobileApp(@RequestBody @ApiParam(name = "移动应用工程", value = "JSON", required = true) mobileAppProject: MobileAppProject): MobileAppBuilderResponse {
        log.debug(mobileAppProject)
        mobileAppProjectService.saveMobileAppProject2DB(mobileAppProject)
        val userProjectDir = mobileAppProjectService.prepareMobileAppProjectDir(mobileAppProject)

        return try {
            appBuilder = iosAppBuilder {
                this.project = mobileAppProject
                this.userProjectDir = userProjectDir
                this.addResources = { mobileAppProjectService.createMobileAppProjectFiles(projectWWWDir, project) }
            }
            log.debug("ios app installer : ${appBuilder?.appInstaller}")
            MobileAppBuilderResponse(
                    status = "Start download ${appBuilder?.project?.username} - ${appBuilder?.project?.projectName} app installer ...",
                    downloadUrl = "/app/download/${mobileAppProject.username}/${mobileAppProject.projectName}")
        } catch (e: IOException) {
            log.error(e)
            MobileAppBuilderResponse("can not init cordova project")
        }
    }


    /**
     * 下载文件的接口
     * @param username 用户名
     * @param projectName 工程名
     * @return 文件类型的响应
     */
    @ApiOperation(value = "下载应用安装包", notes = "返回编译好的移动应用安装包数据")
    @GetMapping("/app/download/{username:.+}/{projectName:.+}")
    fun downloadAndroidApp(@PathVariable @ApiParam(name = "username", value = "用户名") username: String,
                           @PathVariable @ApiParam(name = "projectName", value = "工程名") projectName: String): ResponseEntity<Resource> {
        return if (appBuilder != null) {
            val appResource = mobileAppProjectService.loadFileAsResource(Paths.get(appBuilder!!.appInstaller))
            ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + appResource.filename + "\"")
                    .body(appResource)
        } else {
            ResponseEntity.noContent().build()
        }
    }
}