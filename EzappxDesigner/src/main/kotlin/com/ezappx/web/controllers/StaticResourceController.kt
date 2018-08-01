package com.ezappx.web.controllers

import com.ezappx.web.properties.FileStorageProperties
import com.ezappx.web.services.FileStorageService
import com.mongodb.MongoGridFSException
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/resource/static")
class StaticResourceController(@Autowired private val fileStorageService: FileStorageService,
                               @Autowired private val fileStorageProperties: FileStorageProperties) {

    private val log: Log = LogFactory.getLog(StaticResourceController::class.java)

    @GetMapping("/{fileId:.+}")
    fun downloadData(@PathVariable fileId: String): ResponseEntity<Resource> {
        try {
            val gridFSFile = fileStorageService.loadBinaryData(fileId)

            // 本地文件测试
//            Files.copy(
//                    gridFSFile.inputStream,
//                    Paths.get(fileStorageProperties.uploadDir).toAbsolutePath().normalize().resolve(gridFSFile.filename),
//                    StandardCopyOption.REPLACE_EXISTING)
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"${gridFSFile.filename}\"")
                    .contentLength(gridFSFile.contentLength())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(gridFSFile)
        } catch (e: MongoGridFSException) {
            log.error(e.message)
            return ResponseEntity.notFound().build()
        }
    }
}