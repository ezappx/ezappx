package com.ezappx.web.controllers

import com.ezappx.web.models.MobileAppBuilderResponse
import com.ezappx.web.models.MobileAppBuilderConfig
import com.ezappx.web.models.MobileAppProject
import com.ezappx.web.services.ExportMobileAppService
import org.apache.commons.logging.LogFactory
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.async.WebAsyncTask
import java.util.concurrent.Callable

@RestController
@RequestMapping("/mobile")
class ExportMobileAppController(private val exportService: ExportMobileAppService) {

    private val log = LogFactory.getLog(ExportMobileAppController::class.java)

    /**
     * 发送打包请求前的预处理，首先存储至数据库
     */
    @RequestMapping("/export", method = [RequestMethod.POST])
    fun export(@RequestBody mobileBuilderConfig: MobileAppBuilderConfig, authentication: Authentication): WebAsyncTask<MobileAppBuilderResponse> {
        //TODO timeout is not reasonable
        val webAsyncTask = WebAsyncTask<MobileAppBuilderResponse>(10000, Callable {
            val mobileAppProject = MobileAppProject(authentication.name, authentication.name, mobileBuilderConfig)
            exportService.sendBuildMobileAppRequest(mobileAppProject)
        })

        webAsyncTask.onCompletion {
            log.debug("completed")
        }

        webAsyncTask.onTimeout {
            MobileAppBuilderResponse("build app time out")
        }

        webAsyncTask.onError {
            MobileAppBuilderResponse("build app error")
        }
        return webAsyncTask
    }
}