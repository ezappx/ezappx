package com.ezapp.webdesigner.controllers

import com.fasterxml.jackson.databind.ObjectMapper
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
class ExportController {

    private val log = LogFactory.getLog(ExportController::class.java)

    /**
     * 传送打包资源前执行
     */
    @RequestMapping("/build", method = [RequestMethod.POST])
    fun collectResources(@RequestBody exportConfig: Map<String, Any>, @Autowired restTemplate: RestTemplate) {
        // TODO api应该在插件中提供设置，或在export config 的json中提供
        val buildApi = "http://localhost:8081/api/v1/android/build"
        log.debug("receive the export request")
        log.debug(exportConfig)
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity(ObjectMapper().writeValueAsString(exportConfig), headers)
        val response = restTemplate.postForObject(buildApi, entity, String.javaClass)
        log.debug(response)
    }
}