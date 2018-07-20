package com.ezappx.web.repositories

import com.ezappx.web.models.MobileAppProject
import org.springframework.data.mongodb.repository.MongoRepository

interface UserProjectRepository : MongoRepository<MobileAppProject, String> {
    fun findByProjectId(projectId: String): List<MobileAppProject>
}