package com.ezappx.mobilebuilder.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.List;

/**
 * Run system command in one command line by using cmd1 && cmd2 ...
 */
public class ProcessUtils {
    private Logger log = LoggerFactory.getLogger(ProcessUtils.class);

    private StringBuilder processOutput = new StringBuilder();

    private Path inDir;

    public ProcessUtils(Path inDir) {
        this.inDir =inDir;
    }

    private String runAndRead(Process process) {
        String output = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            for (output = reader.readLine(); output != null; output = reader.readLine()) {
                log.debug(output);
                processOutput.append(output);
            }
        } catch (IOException e) {
            log.error(e.toString());
        }
        return processOutput.toString();
    }

    public String exec(List<String> command) throws IOException, InterruptedException {
        log.debug("exec {}", command);
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.directory(inDir.toFile());
        Process process = processBuilder.start();
        runAndRead(process);
        process.waitFor();
        return processOutput.toString();
    }
}
