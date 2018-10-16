package com.ezappx.builder.services

import com.ezappx.builder.models.MobileAppProject
import com.ezappx.builder.models.MobileAppProjectFile
import com.ezappx.builder.properties.MobileAppBuilderProperty
import com.ezappx.builder.repositories.MobileAppProjectRepository
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service
import org.springframework.util.FileSystemUtils
import java.io.FileNotFoundException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

/**
 * [MobileAppProject]工具类
 */
@Service
class MobileAppProjectService(
        @Autowired private val mobileAppProjectRepository: MobileAppProjectRepository,
        @Autowired private val mongoOperations: MongoOperations,
        @Autowired private val mobileAppBuilderProperty: MobileAppBuilderProperty) {

    private val log = LogFactory.getLog(MobileAppProject::class.java)
    private val userProjectDir = mobileAppBuilderProperty.baseDir.resolve(mobileAppBuilderProperty.userProjectDirName)

    /**
     * 保存移动应用工程到数据库
     * @param mobileAppProject 移动应用工程
     */
    fun saveMobileAppProject2DB(mobileAppProject: MobileAppProject) {
        val oldMobileAppProject = mobileAppProjectRepository.findByUsernameAndProjectName(mobileAppProject.username, mobileAppProject.projectName).firstOrNull()
        if (oldMobileAppProject != null) {
            // 更新数据库已有内容
            mobileAppProject.id = oldMobileAppProject.id
            mobileAppProjectRepository.save(mobileAppProject)
            log.debug("update mobile app project in db")
        } else {
            // 新增到数据库
            mobileAppProjectRepository.save(mobileAppProject)
            log.debug("create new mobile app project and save to db")
        }
    }

    /**
     * 创建 $UserProjects/$username文件夹作为Cordova工程目录
     * @param mobileAppProject 移动应用工程
     * @return Cordova工程目录
     */
    fun prepareMobileAppProjectDir(mobileAppProject: MobileAppProject): Path {
        val projectDir = userProjectDir.resolve(mobileAppProject.username)
        Files.createDirectories(projectDir)
        return projectDir
    }

    /**
     * 从[mobileAppProject]生成移动应用工程文件，并保存到[destinationDir]目录
     * @param destinationDir 保存目录
     * @param mobileAppProject 移动应用工程
     */
    fun createMobileAppProjectFiles(destinationDir: Path, mobileAppProject: MobileAppProject) {
        // 删除 destinationDir 内的所有文件
        FileSystemUtils.deleteRecursively(destinationDir)
        // 获取HTML和CSS文件
        val query = Query(Criteria.where("_id").`in`(mobileAppProject.binaryFiles))
        val files = mongoOperations.find(query, MobileAppProjectFile::class.java)
        files.forEach {
            val file = destinationDir.resolve(it.filePath).toAbsolutePath().normalize()
            // 创建所需的父目录
            log.debug("create file $file")
            Files.createDirectories(file.parent)
            Files.write(file, it.content.toByteArray(), StandardOpenOption.CREATE)
        }
    }

    /**
     * 将文件作为Resource读取
     * @param file 文件
     * @return file对应的Resource
     */
    fun loadFileAsResource(file: Path): Resource {
        val resource = UrlResource(file.toUri())
        if (resource.exists()) {
            return resource
        } else {
            throw FileNotFoundException("file not found at $file")
        }
    }

//    /**
//     * 获取编译好的Android应用安装包
//     * cordova-android@6.4 : platforms/android/build/outputs/apk/debug/android-debug.apk
//     * @param username 用户名
//     * @param projectName 工程名
//     * @return Android应用安装包
//     */
//    fun androidAppUri(username: String, projectName: String): Path = Paths.get(
//            userProjectDir.toAbsolutePath().toString()
//            , username, projectName,
//            "platforms", "android", "build", "outputs", "apk", "debug", "android-debug.apk")
//
//    // TODO 删除原有www文件夹内容
}