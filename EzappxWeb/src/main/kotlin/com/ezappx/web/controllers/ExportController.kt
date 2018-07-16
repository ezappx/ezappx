package com.ezappx.web.controllers

import com.ezappx.web.models.ExportResponse
import com.ezappx.web.models.MobileBuilder
import com.ezappx.web.models.MobileBuilderProperties
import com.ezappx.web.services.ExportService
import org.apache.commons.logging.LogFactory
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.async.WebAsyncTask
import java.util.concurrent.Callable

@RestController
@RequestMapping("/mobile")
class ExportController(private val mobileBuilderProperties: MobileBuilderProperties,
                       private val exportService: ExportService) {

    private val log = LogFactory.getLog(ExportController::class.java)

    /**
     * 发送打包请求前的预处理
     */
    @RequestMapping("/export", method = [RequestMethod.POST])
    fun export(@RequestBody exportConfig: MobileBuilder): WebAsyncTask<ExportResponse> {
        log.debug(exportConfig)
        val remoteMobileInstallerBuilderApi = when (exportConfig.mobileOS.toUpperCase()) {
            "ANDROID" -> mobileBuilderProperties.android
            "IOS" -> mobileBuilderProperties.ios
            else -> throw IllegalArgumentException("not supported mobile OS ${exportConfig.mobileOS}")
        }
        log.debug("remote mobile builder: $remoteMobileInstallerBuilderApi")

        //TODO timeout is not reasonable
        val webAsyncTask = WebAsyncTask<ExportResponse>(3000, Callable {
            exportService.postMobileInstallerBuilderConfig(remoteMobileInstallerBuilderApi, exportConfig)
        })

        webAsyncTask.onCompletion {
            log.debug("completed")
        }

        webAsyncTask.onTimeout {
            log.error("time out")
            ExportResponse("post to $remoteMobileInstallerBuilderApi time out")
        }

        webAsyncTask.onError {
            log.error("post error")
            ExportResponse("cannot post to $remoteMobileInstallerBuilderApi")
        }
        return webAsyncTask
    }
}