package com.ezappx.web.repositories

import com.ezappx.web.models.DesignerStorage
import org.springframework.data.mongodb.repository.MongoRepository

interface DesignerStorageRepository: MongoRepository<DesignerStorage, String> {
    fun findByUsernameAndProjectName(username: String, projectName: String): List<DesignerStorage>
}