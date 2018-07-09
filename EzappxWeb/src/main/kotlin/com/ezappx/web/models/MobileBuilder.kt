package com.ezappx.web.models

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id

data class MobileBuilder(
        @Id @JsonIgnore val id: String,
        var uuid: String,
        var mobileOS: String,
        var customHTMLFiles: List<CustomHTMLFiles>,
        var customCSSFiles: List<CustomCSSFiles>,
        var dependentFiles: DependentFiles
)

data class CustomHTMLFiles(var filename: String, var content: String)

data class CustomCSSFiles(var filename: String, var content: String)

data class DependentFiles(var css: List<String>, var js: List<String>)
