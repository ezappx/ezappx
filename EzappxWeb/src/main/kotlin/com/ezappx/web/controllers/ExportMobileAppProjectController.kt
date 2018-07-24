package com.ezappx.web.controllers

import com.ezappx.web.models.MobileAppProject
import com.ezappx.web.models.MobileAppProjectFile
import com.ezappx.web.responses.MobileAppBuilderResponse
import com.ezappx.web.responses.UploadFileResponse
import com.ezappx.web.services.ExportMobileAppService
import com.ezappx.web.services.StorageService
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.async.WebAsyncTask
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.concurrent.Callable

@RestController
@RequestMapping("/export")
class ExportMobileAppProjectController(
        @Autowired private val exportService: ExportMobileAppService,
        @Autowired private val fileStorageService: StorageService) {

    private val log = LogFactory.getLog(ExportMobileAppProjectController::class.java)

    /**
     * 发送打包请求前的预处理，首先存储至数据库
     */
    @PostMapping("/sendBuildRequest")
    fun export(@RequestBody mobileAppProject: MobileAppProject, authentication: Authentication): WebAsyncTask<MobileAppBuilderResponse> {
        // TODO timeout is not reasonable
        // TODO js插件中静态资源入库，仅保留资源id
        val webAsyncTask = WebAsyncTask<MobileAppBuilderResponse>(10000, Callable {
            exportService.sendBuildMobileAppRequest(mobileAppProject)
        })

        webAsyncTask.onCompletion {
            log.debug("completed")
        }

        webAsyncTask.onTimeout {
            MobileAppBuilderResponse("build app time out")
        }

        webAsyncTask.onError {
            MobileAppBuilderResponse("build app error")
        }
        return webAsyncTask
    }

    @PostMapping("/upload/projectFile")
    fun uploadMobileAppProject(@RequestBody mobileAppProjectFile: MobileAppProjectFile): UploadFileResponse {
//        log.debug(mobileAppProjectFile)
        val id = fileStorageService.storeFileInDB(mobileAppProjectFile)
        return UploadFileResponse("uploaded ${mobileAppProjectFile.filePath}, id is $id")
    }

    @PostMapping("/upload/binaryData")
    fun uploadBinaryData(@RequestParam("file") file: MultipartFile): UploadFileResponse {
        val fileName = fileStorageService.storeFileInDir(file)
        val fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString()
        return UploadFileResponse(fileDownloadUri)
    }
}