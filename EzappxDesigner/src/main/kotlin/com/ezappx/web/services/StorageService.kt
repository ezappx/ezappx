package com.ezappx.web.services

import com.ezappx.web.models.MobileAppProjectFile
import com.ezappx.web.properties.FileStorageProperties
import com.ezappx.web.repositories.MobileAppProjectFileRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

@Service
class StorageService(
        @Autowired private val fileStorageProperties: FileStorageProperties,
        @Autowired private val mobileAppProjectFileRepository: MobileAppProjectFileRepository) {

    private val fileStorageLocation = Paths.get(fileStorageProperties.uploadDir).toAbsolutePath().normalize()

    init {
        Files.createDirectories(fileStorageLocation)
    }

    fun storeFile2Dir(file: MultipartFile): String {
        val fileName = StringUtils.cleanPath(file.originalFilename!!)
        val targetLocation = this.fileStorageLocation.resolve(fileName)
        Files.copy(file.inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING)
        return fileName
    }

    fun storeProject2DB(mobileAppProjectFile: MobileAppProjectFile): String? {
        val oldFile = mobileAppProjectFileRepository.findByUsernameAndProjectNameAndFilePath(
                mobileAppProjectFile.username, mobileAppProjectFile.projectName, mobileAppProjectFile.filePath
        ).firstOrNull()
        if (oldFile != null) {
            mobileAppProjectFile.id = oldFile.id
        }
        return mobileAppProjectFileRepository.save(mobileAppProjectFile).id
    }

    // TODO 上传文件到mongodb
    fun storeBinaryData2Db(file: MultipartFile): String {
        return "file id"
    }
}