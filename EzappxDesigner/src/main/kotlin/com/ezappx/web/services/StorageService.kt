package com.ezappx.web.services

import com.ezappx.web.models.MobileAppProjectFile
import com.ezappx.web.properties.FileStorageProperties
import com.ezappx.web.repositories.MobileAppProjectFileRepository
import com.mongodb.BasicDBObject
import com.mongodb.MongoGridFSException
import com.mongodb.client.gridfs.GridFSBuckets
import com.mongodb.client.gridfs.model.GridFSFile
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.MongoDbFactory
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.gridfs.GridFsCriteria
import org.springframework.data.mongodb.gridfs.GridFsOperations
import org.springframework.data.mongodb.gridfs.GridFsResource
import org.springframework.stereotype.Service
import org.springframework.util.DigestUtils
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

@Service
class StorageService(
        @Autowired private val fileStorageProperties: FileStorageProperties,
        @Autowired private val mobileAppProjectFileRepository: MobileAppProjectFileRepository,
        @Autowired private val gridFsOperations: GridFsOperations,
        @Autowired private val mongoDbFactory: MongoDbFactory) {

    private val log: Log = LogFactory.getLog(StorageService::class.java)
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
        // 数据库存在相同文件时直接返回数据ID
        val sameFile = queryFileByMD5(calcMD5(file))
        if (sameFile != null) {
            return sameFile.objectId.toString()
        }

        // 不存在相同文件时数据入库
        val metaData = BasicDBObject()
        metaData["username"] = username
        metaData["projectName"] = projectName
        metaData["contentType"] = file.contentType
        log.debug(metaData)
        val fileId = gridFsOperations.store(file.inputStream, StringUtils.cleanPath(file.originalFilename!!), metaData)
        return fileId.toString()
    }

    private fun calcMD5(file: MultipartFile): String = DigestUtils.md5DigestAsHex(file.bytes).toLowerCase()

    private fun queryFileByMD5(md5: String): GridFSFile? = gridFsOperations.findOne(Query.query(GridFsCriteria.where("md5").`is`(md5)))

    fun loadBinaryData(fileId: String): GridFsResource {
        val file = gridFsOperations.findOne(Query.query(GridFsCriteria.where("_id").`is`(fileId)))
        if (file != null) {
            return GridFsResource(file, getGridFs().openDownloadStream(file.objectId))
        } else {
            throw MongoGridFSException("file $fileId not found")
        }
    }

    private fun getGridFs() = GridFSBuckets.create(mongoDbFactory.db)
}