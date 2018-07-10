package com.ezappx.web.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class EzappxWebController {

    @RequestMapping("/designer")
    fun ezappxWeb(): String {
        // TODO verify the session
        return "EzappxWeb"
    }
}