package com.ezappx.web.repositories

import com.ezappx.web.models.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository: MongoRepository<User, String> {
    fun findByUsername(username: String): List<User>
}