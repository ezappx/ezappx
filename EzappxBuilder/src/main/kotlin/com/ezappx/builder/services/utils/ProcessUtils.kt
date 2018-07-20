package com.ezappx.builder.services.utils

import org.apache.commons.logging.LogFactory
import java.io.*

object ProcessUtils {
    private val log = LogFactory.getLog(ProcessUtils.javaClass)
    fun readProcessOutput(process: Process) {
        read(process.inputStream, System.out)
        read(process.errorStream, System.err)
    }

    private fun read(inputStream: InputStream, out: PrintStream) {
        try {
            val reader = BufferedReader(InputStreamReader(inputStream))
            var line: String? = reader.readLine()
            while (line != null) {
                log.debug(line)
                line = reader.readLine()
            }
        } catch (e: IOException) {
            log.error(e)
        } finally {
            try {
                inputStream.close()
            } catch (e: IOException) {
                log.error(e)
            }
        }
    }
}