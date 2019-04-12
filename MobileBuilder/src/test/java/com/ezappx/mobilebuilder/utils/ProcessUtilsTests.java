package com.ezappx.mobilebuilder.utils;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ProcessUtilsTests {

    @Test
    public void execTest() throws IOException, InterruptedException {
        ProcessUtils utils = new ProcessUtils(Paths.get(System.getProperty("user.dir")));
        List<String> commands = Arrays.asList("cordova", "create", "project");
        utils.exec(commands);
    }
}
