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
    private var outputLine: String? = null
    /**
     * 最后输出行
     */
    var lastOutputLine: String = ""

    /**
     * 执行[process]并输出内容到终端
     * @param process 将要运行的进程
     */
    private fun runAndRead(process: Process) {
        try {
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            outputLine = reader.readLine()
            while (outputLine != null) {
                log.debug(outputLine)
                lastOutputLine = outputLine!!
                outputLine = reader.readLine()
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
     * @return 最后输出行
     */
    fun exec(atDir: Path, command: List<String>):String {
        log.debug("exec $command")
        val processBuilder = ProcessBuilder(command)
        processBuilder.directory(atDir.toFile())
        val process = processBuilder.start()
        runAndRead(process)
        process.waitFor()
        return lastOutputLine
    }
}