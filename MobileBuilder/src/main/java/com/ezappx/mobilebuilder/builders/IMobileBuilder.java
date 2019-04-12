package com.ezappx.mobilebuilder.builders;

import com.ezappx.mobilebuilder.models.UserMobileProject;
import com.ezappx.mobilebuilder.properties.MobileBuilderProperties;

public interface IMobileBuilder {
    void build(UserMobileProject userMobileProject, MobileBuilderProperties properties);
}
