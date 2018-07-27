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

@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("/api/v1/android")
class MobileAppBuilderController(@Autowired private val mobileAppProjectService: MobileAppProjectService) {
    private val log = LogFactory.getLog(MobileAppBuilderController::class.java)

    @ApiOperation(value = "获取可用移动操作系统平台", notes = "可用平台取决于部署环境")
    @RequestMapping("/available", method = [RequestMethod.GET])
    fun availableMobileOS() = listOf("Android", "iOS")

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
                this.androidMinSDK="18"
            }
            MobileAppBuilderResponse(status = "Start download ${builder.project.username} - ${builder.project.projectName} app installer ...",
                    downloadUrl = "/android/download/${mobileAppProject.username}/${mobileAppProject.projectName}")
        } catch (e: IOException) {
            log.error(e)
            MobileAppBuilderResponse("can not init cordova project")
        }
    }

    @GetMapping("/download/{username:.+}/{projectName:.+}")
    fun downloadAndroidApp(@PathVariable username: String, @PathVariable projectName: String): ResponseEntity<Resource> {
        val appUri = mobileAppProjectService.androidAppUri(username, projectName)
        val appResource = mobileAppProjectService.loadFileAsResource(appUri)
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + appResource.filename + "\"")
                .body(appResource)
    }
}