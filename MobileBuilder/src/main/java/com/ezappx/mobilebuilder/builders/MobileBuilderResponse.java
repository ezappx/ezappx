package com.ezappx.mobilebuilder.builders;

import java.time.LocalDateTime;

public class MobileBuilderResponse {

    public String mobileInstaller;
    public String builderLog;
    public LocalDateTime builtTime;

    public MobileBuilderResponse() {

    }

    public String getMobileInstaller() {
        return mobileInstaller;
    }

    public void setMobileInstaller(String mobileInstaller) {
        this.mobileInstaller = mobileInstaller;
    }

    public String getBuilderLog() {
        return builderLog;
    }

    public void setBuilderLog(String builderLog) {
        this.builderLog = builderLog;
    }

    public LocalDateTime getBuiltTime() {
        return builtTime;
    }

    public void setBuiltTime(LocalDateTime builtTime) {
        this.builtTime = builtTime;
    }
}
