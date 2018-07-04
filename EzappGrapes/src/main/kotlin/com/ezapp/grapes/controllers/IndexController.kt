package com.ezapp.grapes.controllers

import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController {

    private val log = LogFactory.getLog(IndexController::class.java)

    @GetMapping("/")
    fun index(model: Model): String {
        log.debug("rendering index html")
        model["title"] = "Ezapp"
        return "EzappGrapes"
    }
}