package com.ezappx.mobilebuilder.controllers;

import com.ezappx.mobilebuilder.MobileBuilderApplication;
import com.ezappx.mobilebuilder.models.UserMobileProject;
import com.ezappx.mobilebuilder.properties.MobileBuilderProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MobileBuilderProperties.class)

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = MobileBuilderApplication.class)
@AutoConfigureMockMvc
public class MobileBuilderControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void sendAndroidBuildRequest() throws Exception {
        UserMobileProject userMobileProject = new UserMobileProject();
        userMobileProject.username = "test user";
        userMobileProject.projectName = "test project";
        userMobileProject.packageName = "com.ezappx.test.project";
        userMobileProject.cordovaPlugins = Arrays.asList("test-plugin1", "test-plugin2");
        MvcResult mvcResult = mockMvc.perform(
                post("/api/1/android/build")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(userMobileProject))).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}
