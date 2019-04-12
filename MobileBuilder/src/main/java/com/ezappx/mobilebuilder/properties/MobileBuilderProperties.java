package com.ezappx.mobilebuilder.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "ezappx.mobile.builder")
public class MobileBuilderProperties {
    private String projectDirName;
    private String packageNamePrefix;
    private Path baseDir = Paths.get(System.getProperty("user.dir"));

    public String getProjectDirName() {
        return projectDirName;
    }

    public void setProjectDirName(String projectDirName) {
        this.projectDirName = projectDirName;
    }

    public String getPackageNamePrefix() {
        return packageNamePrefix;
    }

    public void setPackageNamePrefix(String packageNamePrefix) {
        this.packageNamePrefix = packageNamePrefix;
    }

    public Path getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(Path baseDir) {
        this.baseDir = baseDir;
    }
}
