package com.ezappx.web.services

import com.ezappx.web.models.MobileAppProjectFile
import com.ezappx.web.properties.FileStorageProperties
import com.ezappx.web.repositories.MobileAppProjectFileRepository
import com.mongodb.BasicDBObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.gridfs.GridFsTemplate
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

@Service
class StorageService(
        @Autowired private val fileStorageProperties: FileStorageProperties,
        @Autowired private val mobileAppProjectFileRepository: MobileAppProjectFileRepository,
        @Autowired private val gridFsTemplate: GridFsTemplate) {

    private val fileStorageLocation = Paths.get(fileStorageProperties.uploadDir).toAbsolutePath().normalize()

    init {
        Files.createDirectories(fileStorageLocation)
    }

    fun storeFile2Dir(username: String, projectName: String, file: MultipartFile): String {
        val userFileStorageDir = fileStorageLocation.resolve(username).resolve(projectName)
        Files.createDirectories(userFileStorageDir)
        val fileName = StringUtils.cleanPath(file.originalFilename!!)
        Files.copy(file.inputStream, userFileStorageDir.resolve(fileName), StandardCopyOption.REPLACE_EXISTING)
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

    fun storeBinaryData2Db(username: String, projectName: String, file: MultipartFile): String {
        val metaData = BasicDBObject()
        metaData["username"] = username
        metaData["projectName"] = projectName
        val fileId = gridFsTemplate.store(file.inputStream, file.name, file.contentType, metaData)
        return fileId.toString()
    }
}