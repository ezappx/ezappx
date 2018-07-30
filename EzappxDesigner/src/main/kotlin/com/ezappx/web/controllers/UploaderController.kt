package com.ezappx.web.controllers

import com.ezappx.web.models.MobileAppProjectFile
import com.ezappx.web.responses.UploadFileResponse
import com.ezappx.web.services.StorageService
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/upload")
class UploaderController(@Autowired private val fileStorageService: StorageService) {

    private val log = LogFactory.getLog(UploaderController::class.java)

    /**
     * 上传html和css资源到数据库
     */
    @PostMapping("/projectFile")
    fun uploadMobileAppProject(@RequestBody mobileAppProjectFile: MobileAppProjectFile): UploadFileResponse {
        val id = fileStorageService.storeProject2DB(mobileAppProjectFile)
        return if (id != null) {
            UploadFileResponse(fileId = id, status = "uploaded ${mobileAppProjectFile.filePath}, id is $id")
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
    fun uploadBinaryData(@RequestParam("files[]") file: MultipartFile): UploadFileResponse {
        val fileName = fileStorageService.storeFile2Dir(file)
        val fileId = fileStorageService.storeBinaryData2Db(file)
        return UploadFileResponse("http://uploader/$fileName", "test-id")
    }

}