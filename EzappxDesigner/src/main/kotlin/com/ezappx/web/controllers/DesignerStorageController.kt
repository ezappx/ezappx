package com.ezappx.web.controllers

import com.ezappx.web.models.DesignerStorage
import com.ezappx.web.services.DesignerStorageServices
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/storage")
class DesignerStorageController(@Autowired private val designerStorageServices: DesignerStorageServices) {
    private val log: Log = LogFactory.getLog(DesignerStorageController::class.java)

    @PostMapping("/store/{username:.+}/{projectName:.+}")
    fun store(@PathVariable username: String,
              @PathVariable projectName: String,
              @RequestBody storage: String) {
        designerStorageServices.saveDesignerStorage2Db(DesignerStorage(username, projectName, storage))
    }

    @GetMapping("/load/{username:.+}/{projectName:.+}", produces = ["application/json"])
    @ResponseBody
    fun load(@PathVariable username: String,
             @PathVariable projectName: String): String {
        return designerStorageServices.loadDesignerStorageFromDb(username, projectName)
    }
}