package com.ezappx.web.security

import com.ezappx.web.repositories.UserRepository
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.stereotype.Service

@Service
class EzappUserDetailService : UserDetailsService {

    private val log = LogFactory.getLog(EzappUserDetailService::class.java)

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findByUsername(username!!).firstOrNull()
        if (user != null) {
//            log.debug(user)
            return User(username, user.password, listOf(SimpleGrantedAuthority("ROLE_USER")))
        } else throw UsernameNotFoundException("$username not found")
    }

    @Bean
    fun passwordEncoder() = NoOpPasswordEncoder.getInstance() as NoOpPasswordEncoder
}