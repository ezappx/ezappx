package com.ezappx.web.services

import com.ezappx.web.responses.MobileAppBuilderResponse
import com.ezappx.web.models.MobileAppProject
import com.ezappx.web.properties.MobileAppBuilderAPI
import com.ezappx.web.repositories.UserProjectRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.logging.LogFactory
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

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
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity(ObjectMapper().writeValueAsString(mobileAppProject), headers)
        val response = restTemplate.postForObject(
                resolveRemoteBuilderServerApi(mobileAppProject.mobileOS),
                entity,
                MobileAppBuilderResponse::class.java)
        log.debug(response)
        return response ?: MobileAppBuilderResponse("no response from remote server")
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