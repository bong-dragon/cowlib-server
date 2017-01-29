package com.cowlib.controller;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.Application;
import com.cowlib.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("local")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class AuthContollerTest {

    private MockMvc mockMvc;

    @Autowired
    AuthContoller authContoller;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(authContoller).build();
    }

    @Ignore
    @Test
    @DatabaseSetup(value = "/dbunit/empty.xml", type = DatabaseOperation.CLEAN_INSERT)
    @ExpectedDatabase(value = "/dbunit/old_user.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void loginFromNode() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/v1/auth")
                .param("facebookId", "1002237446564959")
                .param("name", "테스터")
                .param("profile", "https://profile"))
                .andExpect(status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), User.class);

        assertThat(user.getName()).isEqualTo("테스터");
        assertThat(user.getFacebookId()).isEqualTo("1002237446564959");
    }

    @Ignore
    @Test
    @DatabaseSetup(value = "/dbunit/old_user.xml", type = DatabaseOperation.CLEAN_INSERT)
    @ExpectedDatabase(value = "/dbunit/old_user.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void loginFromNode_old_user() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/v1/auth")
                .param("facebookId", "1002237446564959")
                .param("name", "테스터")
                .param("profile", "https://profile"))
                .andExpect(status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), User.class);

        assertThat(user.getName()).isEqualTo("테스터");
        assertThat(user.getFacebookId()).isEqualTo("1002237446564959");
    }
}