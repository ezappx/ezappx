package com.ezappx.mobilebuilder.utils;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CordovaTests {
    private Path testDir = Paths.get(System.getProperty("user.dir"));

    private ICordova cordova = new Cordova(testDir);

    @Test
    public void cordovaCommandTest() {
        cordova.create("test-dir", "com.ezappx.test", "test-project");
        cordova.addPlatform("android", "1.0.0");
        cordova.addPlugin("cordova-test-plugin");
        cordova.build("android");
    }
}
