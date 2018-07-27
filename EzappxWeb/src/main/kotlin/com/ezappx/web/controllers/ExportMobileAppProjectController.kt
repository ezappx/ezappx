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
     * 发送编译请求
     */
    @PostMapping("/sendBuildRequest")
    fun export(@RequestBody mobileAppProject: MobileAppProject, authentication: Authentication): WebAsyncTask<MobileAppBuilderResponse> {
        // TODO timeout is not reasonable
        val webAsyncTask = WebAsyncTask<MobileAppBuilderResponse>(120000, Callable {
            exportService.sendBuildMobileAppRequest(mobileAppProject)
        })

        webAsyncTask.onCompletion {
        }

        webAsyncTask.onTimeout {
            MobileAppBuilderResponse("build app time out")
        }

        webAsyncTask.onError {
            MobileAppBuilderResponse("build app error")
        }
        return webAsyncTask
    }

    /**
     * 上传html和css资源到数据库
     */
    @PostMapping("/upload/projectFile")
    fun uploadMobileAppProject(@RequestBody mobileAppProjectFile: MobileAppProjectFile): UploadFileResponse {
        val id = fileStorageService.storeFileInDB(mobileAppProjectFile)
        return if (id != null) {
            UploadFileResponse(fileId = id, status = "uploaded ${mobileAppProjectFile.filePath}, id is $id")
        } else {
            UploadFileResponse("Can not store the file to db")
        }
    }

    /**
     * 上传二进制数据到数据库
     * TODO js插件中静态资源入库，仅保留资源id
     */
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