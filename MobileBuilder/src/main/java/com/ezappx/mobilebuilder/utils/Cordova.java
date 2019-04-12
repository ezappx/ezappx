package com.ezappx.mobilebuilder.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cordova implements ICordova {
    private final Logger log = LoggerFactory.getLogger(Cordova.class);
    private static final String CORDOVA = "cordova";

    private List<List<String>> commandsCache;
    private Path userBuilderProjectsPath;

    public Cordova(Path userBuilderProjectsPath) {
        this.userBuilderProjectsPath = userBuilderProjectsPath;
        commandsCache = new ArrayList<>();
    }

    @Override
    public void create(String dirName, String packageName, String projectName) {
        commandsCache.add(Arrays.asList(CORDOVA, "create", dirName, packageName, projectName));
    }

    @Override
    public void addPlatform(String mobilePlatform, String version) {
        commandsCache.add(Arrays.asList(CORDOVA, "platform", "add",
                mobilePlatform.toLowerCase() + "@" + version));
    }

    @Override
    public void addPlugin(String pluginName) {
        commandsCache.add(Arrays.asList(CORDOVA, "plugin", "add", pluginName));
    }

    @Override
    public void build(String mobilePlatform) {
        ProcessUtils processUtils = new ProcessUtils(userBuilderProjectsPath);
        for (List<String> command : commandsCache) {
            try {
                processUtils.exec(command);
            } catch (Exception e) {
                log.error(e.toString());
            }
        }
    }
}
