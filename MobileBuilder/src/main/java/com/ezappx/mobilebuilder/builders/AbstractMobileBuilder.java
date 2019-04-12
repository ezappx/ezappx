package com.ezappx.mobilebuilder.builders;

import com.ezappx.mobilebuilder.models.UserMobileProject;
import com.ezappx.mobilebuilder.properties.MobileBuilderProperties;
import com.ezappx.mobilebuilder.utils.Cordova;
import com.ezappx.mobilebuilder.utils.ICordova;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * project/
 * --user1/
 * ----builder-project1/
 * ----builder-project2/
 * --user2/
 * ...
 */
public abstract class AbstractMobileBuilder implements IMobileBuilder {
    private Logger log = LoggerFactory.getLogger(AbstractMobileBuilder.class);
    protected MobileBuilderProperties properties;
    protected UserMobileProject userMobileProject;
    protected String packageNamePrefix;
    protected Path userBuilderProjectPath;
    protected Path builderProjectPath;
    protected MobileBuilderResponse mobileBuilderResponse;
    protected ICordova cordova;

    /**
     * Init builder environment
     */
    protected void initBuilderEnv() {
        if (userMobileProject == null) {
            throw new NullPointerException("UserMobileProject not set");
        }

        packageNamePrefix = properties.getPackageNamePrefix();
        userBuilderProjectPath = properties.getBaseDir().resolve(userMobileProject.username);
        builderProjectPath = userBuilderProjectPath.resolve(userMobileProject.projectName);
        cordova = new Cordova(userBuilderProjectPath);
    }

    /**
     * Create local builder project for building
     */
    protected void createBuilderProject() {
        // create user builder projects
        if (!Files.exists(userBuilderProjectPath)) {
            try {
                Files.createDirectories(userBuilderProjectPath);
            } catch (IOException e) {
                log.error(e.toString());
            }
        }

        // create builder projects
        if (!Files.exists(builderProjectPath)) {
            log.info("init ezappx mobile builder project at {}", builderProjectPath.toString());
            cordova.create(
                    userMobileProject.projectName,
                    packageNamePrefix + userMobileProject.packageName,
                    userMobileProject.projectName);
        }
    }

    /**
     * Add cordova plugin that is stored in userMobileProject
     */
    protected void addMobileNativePlugin() {
        for (String plugin : userMobileProject.cordovaPlugins) {
            cordova.addPlugin(plugin);
        }
    }

    protected void preBuild() {
        initBuilderEnv();
        createBuilderProject();
        addMobilePlatform();
        addMobileNativePlugin();
    }

    /**
     * Add specified mobile platform
     */
    protected abstract void addMobilePlatform();

    /**
     * Send response of builder to message queue
     */
    protected abstract void notifyMobileBuilderResponse();
}
