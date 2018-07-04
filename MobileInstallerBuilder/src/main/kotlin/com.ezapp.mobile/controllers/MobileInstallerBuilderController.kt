package com.ezapp.mobile.controllers

import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.apache.commons.logging.LogFactory
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/")
class MobileInstallerBuilderController {
    private val log = LogFactory.getLog(MobileInstallerBuilderController::class.java)

    @ApiOperation(value = "获取可用移动操作系统平台", notes = "可用平台取决于部署环境")
    @RequestMapping("/available", method = [RequestMethod.GET])
    fun availableMobileOS() = listOf("Android", "iOS")

    @ApiOperation(value = "编译Android安装包", notes = "使用Cordova生成移动应用安装包")
    @ApiImplicitParam(name = "JSON配置", value = """
        {
            "uuid": "123",
            "mobileOS": "Android",
            "customHTMLFiles": [
                {
                    "filename": "index.html",
                    "content": "<html> </html>"
                }
            ],
            "customCSSFiles": [
               {
                    "filename": "MyCSS.css",
                    "content": "body {background-color: yellow}"
                }
            ],
            "dependentFiles":
            {
                "css": ["weui.min.css"],
                "js": ["jquery-3.3.1.min.js"]
            },
            "callBackApi":"http://localhost"
        }
    """)
    @RequestMapping("/android/build", method = [RequestMethod.POST])
    fun buildAndroidMobileInstaller(@RequestBody androidBuilderConfig: Map<String, Any>) {
        log.debug("build android installer ...")
        log.debug(androidBuilderConfig)
    }
}