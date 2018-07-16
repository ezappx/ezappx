package com.ezappx.web.controllers

import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class DesignerController {

    @RequestMapping("/designer")
    fun designer(model: Model, authentication: Authentication): String {
        model["username"] = authentication.name
        return "designer"
    }
}