package com.ezappx.web.services

import com.ezappx.web.models.User
import com.ezappx.web.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class EzappUserService(
        @Autowired val userRepository: UserRepository,
        @Autowired val passwordEncoder: PasswordEncoder) {

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
                && user.username.isNotBlank() && user.email.isNotBlank() && user.password!!.isNotBlank()
                && !userExisted(user)) {
            // encode password
            user.password = passwordEncoder.encode(user.password)
            // TODO need handle with failure of saving
            userRepository.save(user)
            true
        } else false
    }
}