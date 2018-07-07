package com.ezappx.builder.models

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