package com.ezappx.web.responses

data class UploadFileResponse(val status: String, val data: List<String>? = null, val fileId: String? = null)