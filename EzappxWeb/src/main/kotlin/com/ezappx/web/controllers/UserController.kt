package com.ezappx.web.controllers

import com.ezappx.web.models.User
import com.ezappx.web.services.EzappUserService
import org.apache.commons.logging.LogFactory
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class UserController(private val userService: EzappUserService) {
    private val log = LogFactory.getLog(UserController::class.java)

    @GetMapping("/")
    fun index(model: Model): String {
        return "redirect:/designer"
    }

    @GetMapping("/login")
    fun login(model: Model): String {
        val auth = SecurityContextHolder.getContext().authentication
        return if (auth is AnonymousAuthenticationToken)
            "login"
        else "redirect:/designer"
    }

    @GetMapping("/register")
    fun register(model: Model): String {
        model["user"] = User()
        return "register"
    }

    @PostMapping("/register")
    fun registerSubmit(@ModelAttribute user: User): String {
        val isSucceeded = userService.createUser(user)
        return if (isSucceeded) "redirect:/login"
        else "redirect:/register"
    }

}