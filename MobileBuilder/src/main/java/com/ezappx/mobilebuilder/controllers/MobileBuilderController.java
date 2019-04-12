package com.ezappx.mobilebuilder.controllers;

import com.ezappx.mobilebuilder.builders.AndroidBuilder;
import com.ezappx.mobilebuilder.builders.IMobileBuilder;
import com.ezappx.mobilebuilder.builders.factory.MobileBuilderFactory;
import com.ezappx.mobilebuilder.models.UserMobileProject;
import com.ezappx.mobilebuilder.properties.MobileBuilderProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1")
public class MobileBuilderController {

    private Logger log = LoggerFactory.getLogger(MobileBuilderController.class);

    @Autowired
    private MobileBuilderFactory mobileBuilderFactory;

    @Autowired
    private MobileBuilderProperties properties;

    @PostMapping(value = "/android/build")
    public void androidBuild(@RequestBody UserMobileProject userMobileProject) {
        log.info("create android builder");
        IMobileBuilder builder = mobileBuilderFactory.getMobileBuilder(AndroidBuilder.class);
        builder.build(userMobileProject, properties);
    }

    @PostMapping("/test")
    public String test() {
        log.debug(properties.getPackageNamePrefix());
        return "test controller";
    }
}
