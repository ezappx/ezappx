package com.ezappx.web.controllers

import com.ezappx.web.models.MobileAppProject
import com.ezappx.web.responses.MobileAppBuilderResponse
import com.ezappx.web.services.ExportMobileAppService
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.async.WebAsyncTask
import java.util.concurrent.Callable

@RestController
@RequestMapping("/export")
class ExportController(@Autowired private val exportService: ExportMobileAppService) {

    private val log = LogFactory.getLog(ExportController::class.java)

    /**
     * 发送编译请求
     */
    @PostMapping("/mobileAppProject")
    fun export(@RequestBody mobileAppProject: MobileAppProject, authentication: Authentication): WebAsyncTask<MobileAppBuilderResponse> {
        // TODO timeout is not reasonable
        val webAsyncTask = WebAsyncTask<MobileAppBuilderResponse>(120000, Callable {
            exportService.sendBuildMobileAppRequest(mobileAppProject)
        })

        webAsyncTask.onCompletion {
            log.debug("built app")
        }

        webAsyncTask.onTimeout {
            log.error("build app timeout")
            MobileAppBuilderResponse("build app timeout")
        }

        webAsyncTask.onError {
            log.error("build app error")
            MobileAppBuilderResponse("build app error")
        }
        return webAsyncTask
    }
}