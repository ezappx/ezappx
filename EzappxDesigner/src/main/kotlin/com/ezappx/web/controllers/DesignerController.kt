package com.ezappx.web.controllers

import com.ezappx.web.properties.FileStorageProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class DesignerController(@Autowired private val fileStorageProperties: FileStorageProperties) {

    /**
     * designer页面逻辑处理，[authentication] 用于用户登陆验证
     */
    @RequestMapping("/designer")
    fun designer(model: Model, authentication: Authentication): String {
        model["username"] = authentication.name
        model["uploadApi"] = fileStorageProperties.uploadApi
        return "designer"
    }
}