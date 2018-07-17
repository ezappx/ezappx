package com.ezappx.web.services

import com.ezappx.web.models.ExportResponse
import com.ezappx.web.models.MobileBuilderConfig
import com.ezappx.web.models.UserProject
import com.ezappx.web.repositories.UserProjectRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.logging.LogFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.time.LocalDateTime

@Service
class ExportService(private val restTemplate: RestTemplate,
                    private val userProjectRepository: UserProjectRepository) {

    private val log = LogFactory.getLog(ExportService::class.java)

    /**
     * POST打包配置JSON到远程打包服务器
     */
    fun postMobileInstallerBuilderConfig(remoteMobileInstallerBuilderApi: String, exportConfig: MobileBuilderConfig): ExportResponse {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity(ObjectMapper().writeValueAsString(exportConfig), headers)
        val response = restTemplate.postForObject(remoteMobileInstallerBuilderApi, entity, ExportResponse::class.java)
        log.debug(response)
        return response ?: ExportResponse("no response from remote server")
    }

    /**
     * 保存导出配置到数据库
     */
    fun saveExportConfig2DB(username: String, exportConfig: MobileBuilderConfig): ExportResponse {
        val userProject = UserProject(username, username, exportConfig, LocalDateTime.now())
        userProjectRepository.save(userProject)
        return ExportResponse("保存至数据库")
    }
}