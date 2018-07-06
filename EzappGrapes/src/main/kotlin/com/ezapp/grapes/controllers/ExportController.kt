package com.ezapp.grapes.controllers

import com.ezapp.grapes.models.MobileBuilder
import com.ezapp.grapes.models.MobileBuilderProperties
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.apache.commons.logging.LogFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("/mobile")
class ExportController(private val mobileBuilderProperties: MobileBuilderProperties,
                       private val mapper: ObjectMapper,
                       private val restTemplate: RestTemplate) {

    private val log = LogFactory.getLog(ExportController::class.java)


    /**
     * 发送打包请求前的预处理
     */
    @RequestMapping("/export", method = [RequestMethod.POST])
    fun collectResources(@RequestBody mobileBuilderJson: String): TmpResponse {
        val exportConfig = mapper.readValue<MobileBuilder>(mobileBuilderJson)
        val remoteMobileInstallerBuilderApi = when (exportConfig.mobileOS.toUpperCase()) {
            "ANDROID" -> mobileBuilderProperties.android
            "IOS" -> mobileBuilderProperties.ios
            else -> throw IllegalArgumentException("not supported mobile OS ${exportConfig.mobileOS}")
        }
        log.debug("remote mobile builder: $remoteMobileInstallerBuilderApi")

        return try {
            postMobileInstallerBuilderConfig(remoteMobileInstallerBuilderApi, exportConfig)
        } catch (e: Exception) {
            log.error("can not post to $remoteMobileInstallerBuilderApi")
            log.error(e)
            TmpResponse("cannot post to $remoteMobileInstallerBuilderApi")
        }
    }

    /**
     * POST打包配置JSON到远程打包服务器
     */
    private fun postMobileInstallerBuilderConfig(remoteMobileInstallerBuilderApi: String, exportConfig: MobileBuilder): TmpResponse{
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity(ObjectMapper().writeValueAsString(exportConfig), headers)
        val response = restTemplate.postForObject(remoteMobileInstallerBuilderApi, entity, TmpResponse::class.java)
        log.debug(response)
        return response ?: TmpResponse("no response from remote server")
    }

    data class TmpResponse(val status: String)
}