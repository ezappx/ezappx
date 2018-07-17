package com.ezappx.web.repositories

import com.ezappx.web.models.UserProject
import org.springframework.data.mongodb.repository.MongoRepository

interface UserProjectRepository : MongoRepository<UserProject, String> {
    fun findByProjectId(projectId: String): List<UserProject>
}