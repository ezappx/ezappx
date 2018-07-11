package com.ezappx.web.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class DesignerController {

    @RequestMapping("/designer")
    fun designer(): String {
        // TODO verify the session
        return "designer"
    }
}