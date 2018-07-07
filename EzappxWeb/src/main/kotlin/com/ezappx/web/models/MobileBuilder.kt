package com.ezappx.web.models

/**
 * Json Example
 *
 *
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
 */


data class CustomHTMLFiles(var filename: String, var content: String)

data class CustomCSSFiles(var filename: String, var content: String)

data class DependentFiles(var css: List<String>, var js: List<String>)


data class MobileBuilder(
        var uuid: String,
        var mobileOS: String,
        var customHTMLFiles: List<CustomHTMLFiles>,
        var customCSSFiles: List<CustomCSSFiles>,
        var dependentFiles: DependentFiles
)
