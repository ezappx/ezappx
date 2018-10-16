package com.ezappx.builder.repositories

import com.ezappx.builder.models.MobileAppProject
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * [MobileAppProject]的DAO
 */
interface MobileAppProjectRepository : MongoRepository<MobileAppProject, String> {
    fun findByUsernameAndProjectName(username: String, projectName: String): List<MobileAppProject>
}