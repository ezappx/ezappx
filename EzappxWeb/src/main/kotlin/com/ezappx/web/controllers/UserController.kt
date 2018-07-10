package com.ezappx.web.controllers

import com.ezappx.web.models.User
import com.ezappx.web.services.UserService
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class UserController(private val userService: UserService) {
    private val log = LogFactory.getLog(UserController::class.java)

    @GetMapping("/")
    fun index(model: Model): String {
        // TODO verify the session
        return "redirect:/login"
    }

    @GetMapping("/login")
    fun login(model: Model): String {
        model["user"] = User()
        return "UserLogin"
    }

    @PostMapping("/login")
    fun loginSubmit(@ModelAttribute user: User): String {
        return if (userService.login(user)) {
            "redirect:/designer"
        } else "redirect:/login"
    }

    @GetMapping("/register")
    fun register(model: Model): String {
        model["user"] = User()
        return "UserRegister"
    }

    @PostMapping("/register")
    fun registerSubmit(@ModelAttribute user: User): String {
        val isSucceeded = userService.createUser(user)
        return if (isSucceeded) "redirect:/login"
        else "redirect:/register"
    }

}