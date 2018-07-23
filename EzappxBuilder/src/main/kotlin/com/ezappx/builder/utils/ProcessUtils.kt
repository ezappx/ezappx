package com.ezappx.builder.utils

import org.apache.commons.logging.LogFactory
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader

class ProcessUtils {
    private val log = LogFactory.getLog(ProcessUtils::class.java)
    lateinit var execInDir: File

    private fun runAndRead(process: Process) {
        log.debug("start")
        try {
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            var line: String? = reader.readLine()
            while (line != null) {
                log.debug(line)
                line = reader.readLine()
            }
        } catch (e: IOException) {
            log.error(e)
        } finally {
            try {
                process.inputStream.close()
            } catch (e: IOException) {
                log.error(e)
            }
        }
    }

    fun exec(command: List<String>) {
        val processBuilder = ProcessBuilder(command)
        processBuilder.inheritIO()
        processBuilder.directory(execInDir)
        val process = processBuilder.start()
        runAndRead(process)
        process.waitFor()
    }
}