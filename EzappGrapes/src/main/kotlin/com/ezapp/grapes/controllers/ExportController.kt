package com.ezapp.grapes.controllers

import com.ezapp.grapes.models.MobileBuilder
import com.ezapp.grapes.models.MobileBuilderProperties
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("/mobile")
class ExportController(private val mobileBuilderProperties: MobileBuilderProperties) {

    private val log = LogFactory.getLog(ExportController::class.java)

    @Autowired
    private lateinit var mapper: ObjectMapper

    @RequestMapping("/build", method = [RequestMethod.POST])
    fun collectResources(@RequestBody mobileBuilderJson: String) {
        val mobileBuildApi = mobileBuilderProperties.android
        log.debug("mobile builder api: $mobileBuildApi")
        log.debug(mobileBuilderJson)
        val mobileBuilder = mapper.readValue<MobileBuilder>(mobileBuilderJson)
        log.debug(mobileBuilder)
    }

    private fun requestBuildMobileInstaller(remoteBuildApi: String,
                                            exportConfig: String,
                                            @Autowired restTemplate: RestTemplate) {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity(ObjectMapper().writeValueAsString(exportConfig), headers)
        val response = restTemplate.postForObject(remoteBuildApi, entity, String.javaClass)
        log.debug(response)
    }
}