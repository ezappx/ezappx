package com.ezappx.web.services

import com.ezappx.web.models.ExportResponse
import com.ezappx.web.models.MobileBuilder
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.logging.LogFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ExportService(private val restTemplate: RestTemplate) {

    private val log = LogFactory.getLog(ExportService::class.java)

    /**
     * POST打包配置JSON到远程打包服务器
     */
    fun postMobileInstallerBuilderConfig(remoteMobileInstallerBuilderApi: String, exportConfig: MobileBuilder): ExportResponse {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity(ObjectMapper().writeValueAsString(exportConfig), headers)
        val response = restTemplate.postForObject(remoteMobileInstallerBuilderApi, entity, ExportResponse::class.java)
        log.debug(response)
        return response ?: ExportResponse("no response from remote server")
    }
}