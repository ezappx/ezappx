package com.ezappx.web.services

import com.ezappx.web.models.MobileAppProject
import com.ezappx.web.properties.MobileAppBuilderAPI
import com.ezappx.web.repositories.UserProjectRepository
import com.ezappx.web.responses.MobileAppBuilderResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.logging.LogFactory
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.net.ConnectException

@Service
class ExportMobileAppService(private val restTemplate: RestTemplate,
                             private val userProjectRepository: UserProjectRepository,
                             private val mongoOperations: MongoOperations,
                             private val mobileAppBuilderAPI: MobileAppBuilderAPI) {

    private val log = LogFactory.getLog(ExportMobileAppService::class.java)

    /**
     * POST打包配置JSON到远程打包服务器
     */
    fun sendBuildMobileAppRequest(mobileAppProject: MobileAppProject): MobileAppBuilderResponse {
        log.debug("export: $mobileAppProject")
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity(ObjectMapper().writeValueAsString(mobileAppProject), headers)
        return try {
            val response = restTemplate.postForObject(
                    resolveRemoteBuilderServerApi(mobileAppProject.mobileOS),
                    entity,
                    MobileAppBuilderResponse::class.java)
            response ?: throw ConnectException("no response from remote server")
        } catch (e: Exception) {
            log.error(e)
            MobileAppBuilderResponse(e.toString())
        }
    }

    /**
     * 获得远程打包服务器API
     */
    private fun resolveRemoteBuilderServerApi(targetMobileOS: String?) = when (targetMobileOS?.toUpperCase()) {
        "ANDROID" -> mobileAppBuilderAPI.android
        "IOS" -> mobileAppBuilderAPI.ios
        else -> throw IllegalArgumentException("not supported mobile OS $targetMobileOS")
    }
}