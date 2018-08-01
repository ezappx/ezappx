package com.ezappx.web.controllers

import com.ezappx.web.models.MobileAppProjectFile
import com.ezappx.web.properties.FileStorageProperties
import com.ezappx.web.responses.UploadFileResponse
import com.ezappx.web.services.FileStorageService
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/upload")
class UploaderController(@Autowired private val fileStorageService: FileStorageService,
                         @Autowired private val fileStorageProperties: FileStorageProperties) {

    private val log = LogFactory.getLog(UploaderController::class.java)

    /**
     * 上传html和css资源到数据库
     */
    @PostMapping("/projectFile")
    fun uploadMobileAppProject(@RequestBody mobileAppProjectFile: MobileAppProjectFile): UploadFileResponse {
        val id = fileStorageService.storeProject2DB(mobileAppProjectFile)
        return if (id != null) {
            UploadFileResponse(status = "uploaded ${mobileAppProjectFile.filePath}, id is $id", fileId = id)
        } else {
            UploadFileResponse("Can not store the file to db")
        }
    }

    /**
     * 上传二进制数据到数据库
     * RequestParam("files[]")是因为grapesjs里内置的上传方法中使用了这个参数，很迷幻
     * TODO js插件中静态资源入库，仅保留资源id
     */
    @PostMapping("/binaryData")
    fun uploadBinaryData(@RequestParam("files[]") file: MultipartFile,
                         @RequestParam("username") username: String,
                         @RequestParam("projectName") projectName: String,
                         authentication: Authentication): UploadFileResponse {
        if (!authentication.isAuthenticated) {
            return UploadFileResponse("Upload file failed. Need authentication")
        }
//        fileStorageService.storeFile2Dir(username, projectName, file)
        val fileId = fileStorageService.storeBinaryData2Db(username, projectName, file)
        val resp = UploadFileResponse("uploaded $fileId", listOf("${fileStorageProperties.resourceApi}/$fileId"), fileId)   // TODO change url to properties       // TODO change url to properties
        log.debug(resp)
        return resp
    }

}