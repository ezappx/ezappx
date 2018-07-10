package com.ezappx.web.services

import com.ezappx.web.models.User
import com.ezappx.web.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {

    fun login(user: User): Boolean {
        return if (user.username != null && user.password != null && user.username.isNotBlank() && user.password.isNotBlank()) {
            val userFromDB = userRepository.findByUsernameAndPassword(user.username, user.password).firstOrNull()
            userFromDB != null
        } else false
    }

    fun userExisted(user: User): Boolean {
        return if (user.username != null && user.email != null) {
            val userFromDBByName = userRepository.findByUsername(user.username).firstOrNull()
            val userFromDBByEmail = userRepository.findByEmail(user.email).firstOrNull()
            !(userFromDBByName == null && userFromDBByEmail == null)
        } else false
    }

    /**
     * 当用户名或邮箱已在数据库时，创建新用户会失败
     */
    fun createUser(user: User): Boolean {
        return if (user.username != null && user.email != null && user.password != null
                && user.username.isNotBlank() && user.email.isNotBlank() && user.password.isNotBlank()
                && !userExisted(user)) {
            // TODO need handle with failure of saving
            userRepository.save(user)
            true
        } else false
    }
}