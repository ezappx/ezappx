package com.ezappx.builder.controllers

import com.ezappx.builder.models.MobileAppProject
import com.ezappx.builder.responses.MobileAppBuilderResponse
import com.ezappx.builder.services.MobileAppProjectService
import com.ezappx.builder.services.androidBuilder
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
@RequestMapping("/api/v1/android")
class MobileAppBuilderController(@Autowired private val mobileAppProjectService: MobileAppProjectService) {
    private val log = LogFactory.getLog(MobileAppBuilderController::class.java)
    private var appInstaller = ""

    /**
     * 目前运行环境可编译的移动操作系统平台类型
     * @return 移动操作系统平台类型
     */
    @ApiOperation(value = "获取可用移动操作系统平台", notes = "可用平台取决于部署环境")
    @RequestMapping("/available", method = [RequestMethod.GET])
    fun availableMobileOS() = listOf("Android", "iOS") //TODO 根据目前操作系统自动适配

    /**
     * 编译Android应用的接口
     * @param mobileAppProject 移动应用工程
     * @return [MobileAppBuilderResponse]
     */
    @ApiOperation(value = "编译Android安装包", notes = "生成移动应用安装包")
    @ApiImplicitParam(name = "JSON文件", value = "移动应用工程")
    @RequestMapping("/build", method = [RequestMethod.POST])
    fun buildAndroidMobileApp(@RequestBody @ApiParam(name = "资源与配置", value = "JSON格式", required = true) mobileAppProject: MobileAppProject): MobileAppBuilderResponse {
        log.debug(mobileAppProject)
        mobileAppProjectService.saveMobileAppProject2DB(mobileAppProject)
        val userProjectDir = mobileAppProjectService.prepareMobileAppProjectDir(mobileAppProject)

        return try {
            val builder = androidBuilder {
                this.project = mobileAppProject
                this.userProjectDir = userProjectDir
                this.addResources = { mobileAppProjectService.createMobileAppProjectFiles(projectWWWDir, project) }
                // TODO 应该放在配置文件或前端项目设置里，测试用4.3版本
                this.androidMinSDK = "18"
            }
            log.debug("apk: ${builder.appInstaller}")
            appInstaller = builder.appInstaller
            MobileAppBuilderResponse(status = "Start download ${builder.project.username} - ${builder.project.projectName} app installer ...",
                    downloadUrl = "/android/download/${mobileAppProject.username}/${mobileAppProject.projectName}")
        } catch (e: IOException) {
            log.error(e)
            resetArgs()
            MobileAppBuilderResponse("can not init cordova project")
        }
    }

    /**
     * 下载文件的接口
     * @param username 用户名
     * @param projectName 工程名
     * @return 文件类型的响应
     */
    @GetMapping("/download/{username:.+}/{projectName:.+}")
    fun downloadAndroidApp(@PathVariable username: String, @PathVariable projectName: String): ResponseEntity<Resource> {
        val appResource = mobileAppProjectService.loadFileAsResource(Paths.get(appInstaller))
        resetArgs()
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + appResource.filename + "\"")
                .body(appResource)
    }

    /**
     * 重置[appInstaller]参数
     */
    private fun resetArgs() {
        appInstaller = ""
    }
}