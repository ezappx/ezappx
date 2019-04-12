package com.ezappx.mobilebuilder.builders;

import com.ezappx.mobilebuilder.models.UserMobileProject;
import com.ezappx.mobilebuilder.properties.MobileBuilderProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AndroidBuilder extends AbstractMobileBuilder {
    private Logger log = LoggerFactory.getLogger(AndroidBuilder.class);
    // TODO Version should be set by Web IDE
    private static final String VERSION = "7.1.0";
    private static final String PLATFORM = "android";

    @Override
    protected void addMobilePlatform() {
        cordova.addPlatform(PLATFORM, VERSION);
    }

    @Override
    protected void notifyMobileBuilderResponse() {
        log.debug("build finished and sent to MQ");
    }

    @Override
    public void build(UserMobileProject userMobileProject, MobileBuilderProperties properties) {
        this.properties = properties;
        this.userMobileProject = userMobileProject;
        preBuild();
        cordova.build(PLATFORM);
    }
}
