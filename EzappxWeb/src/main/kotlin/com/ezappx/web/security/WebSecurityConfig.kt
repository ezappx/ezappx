package com.ezappx.web.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Bean
    override fun userDetailsService() = EzappUserDetailService()

    override fun configure(http: HttpSecurity?) {
        //FIXME 内置的TOMCAT在返回地址时默认localhost，导致nginx代理失败
        http!!.csrf().disable()
        http.authorizeRequests()
                .antMatchers("/", "/register").permitAll()
                .antMatchers("/css/**", "/js/**").permitAll()
                .anyRequest().authenticated().and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/designer", true)
                .permitAll().and()
                .logout().logoutUrl("/logout").permitAll()
        http.authorizeRequests().and().rememberMe()
        http.logout().logoutSuccessUrl("/")
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService())
    }
}
