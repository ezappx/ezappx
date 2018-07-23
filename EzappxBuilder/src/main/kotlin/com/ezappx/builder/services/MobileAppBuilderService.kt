package com.ezappx.builder.services

import com.ezappx.builder.models.MobileAppProject
import com.ezappx.builder.repositories.MobileAppProjectRepository
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Service

@Service
class MobileAppBuilderService(private val mobileAppProjectRepository: MobileAppProjectRepository) {
    private val log = LogFactory.getLog(MobileAppProject::class.java)

    /**
     * 保存导出移动应用工程到数据库
     */
    fun saveMobileAppProject2DB(mobileAppProject: MobileAppProject) {
        val oldMobileAppProject = mobileAppProjectRepository.findByUsernameAndProjectName(mobileAppProject.username, mobileAppProject.projectName).firstOrNull()
        if (oldMobileAppProject != null) {
            // 更新数据库已有内容
            mobileAppProject.id = oldMobileAppProject.id
            mobileAppProjectRepository.save(mobileAppProject)
            log.debug("update mobile app project in db")
        } else {
            //新增到数据库
            mobileAppProjectRepository.save(mobileAppProject)
            log.debug("create new mobile app project and save to db")
        }
    }
}