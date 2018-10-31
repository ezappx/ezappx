package com.ezappx.web.services

import com.ezappx.web.models.DesignerStorage
import com.ezappx.web.repositories.DesignerStorageRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DesignerStorageServices(
        @Autowired private val designerStorageRepository: DesignerStorageRepository) {

    private val log: Log = LogFactory.getLog(DesignerStorageServices::class.java)

    fun saveDesignerStorage2Db(designerStorage: DesignerStorage) {
        val oldData = designerStorageRepository.findByUsernameAndProjectName(designerStorage.username, designerStorage.projectName).firstOrNull()
        if (oldData != null) {
            designerStorage.id = oldData.id
        }
        designerStorageRepository.save(designerStorage)
    }

    fun loadDesignerStorageFromDb(username: String, projectName: String): String {
        val data = designerStorageRepository.findByUsernameAndProjectName(username, projectName).firstOrNull()
        return data?.storage ?: "{}"
    }
}