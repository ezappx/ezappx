package com.ezappx.builder.models

import io.swagger.annotations.ApiModel

@ApiModel(value = "打包配置", description = "通过第三方POST获取")
data class MobileInstallerBuilderConfig(
        var uuid: String,
        var mobileOS: String,
        var customHTMLFiles: List<CustomHTMLFiles>,
        var customCSSFiles: List<CustomCSSFiles>,
        var dependentFiles: DependentFiles
)

data class CustomHTMLFiles(var filename: String, var content: String)

data class CustomCSSFiles(var filename: String, var content: String)

data class DependentFiles(var css: List<String>, var js: List<String>)

enum class AvailableMobileOS {
    ANDROID,
    IOS
}