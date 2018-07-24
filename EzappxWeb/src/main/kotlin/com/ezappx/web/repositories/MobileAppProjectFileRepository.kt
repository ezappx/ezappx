package com.ezappx.web.repositories

import com.ezappx.web.models.MobileAppProjectFile
import org.springframework.data.mongodb.repository.MongoRepository

interface MobileAppProjectFileRepository : MongoRepository<MobileAppProjectFile, String> {
    fun findByUsernameAndProjectNameAndFilePath(username: String, projectName: String, filePath: String): List<MobileAppProjectFile>
}