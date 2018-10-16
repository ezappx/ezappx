package com.ezappx.builder.utils

import org.apache.commons.logging.LogFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.nio.file.Path

/**
 * 进程工具类
 */
object ProcessUtils {
    private val log = LogFactory.getLog(ProcessUtils::class.java)

    /**
     * 执行[process]并输出内容到终端
     * @param process 将要运行的进程
     */
    private fun runAndRead(process: Process) {
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

    /**
     * 新建进程执行命令[command]
     * @param atDir 执行命令所在目录
     * @param command 命令
     */
    fun exec(atDir: Path, command: List<String>) {
        log.debug("exec $command")
        val processBuilder = ProcessBuilder(command)
        processBuilder.inheritIO()
        processBuilder.directory(atDir.toFile())
        val process = processBuilder.start()
        runAndRead(process)
        process.waitFor()
    }
}