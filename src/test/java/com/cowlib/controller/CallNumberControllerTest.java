package com.cowlib.controller;

import com.Application;
import com.cowlib.model.CallNumber;
import com.cowlib.repository.CallNumberRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("local")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class CallNumberControllerTest {

    private MockMvc mockMvc;

    @Autowired
    CallNumberController callNumberController;

    @Autowired
    CallNumberRepository callNumberRepository;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(callNumberController).build();
    }


    @Test
    @DatabaseSetup(value = "/dbunit/basic.xml")
    public void register() throws Exception {
        mockMvc.perform(post("/v1/callNumbers")
                .param("ownerId", "1")
                .param("bookMetaId", "6"))
                .andExpect(status().isOk())
                .andReturn();

        List<CallNumber> callNumbers = callNumberRepository.selectByOwnerId(1, false);
        assertThat(callNumbers.size()).isEqualTo(2);
    }

    @Test
    @DatabaseSetup(value = "/dbunit/basic.xml")
    public void deleteCallNumber() throws Exception {
        mockMvc.perform(delete("/v1/callNumbers")
                .param("id", "10"))
                .andExpect(status().isOk())
                .andReturn();

        List<CallNumber> callNumbers = callNumberRepository.selectByOwnerId(1, true);
        System.out.println(callNumbers);
        assertThat(callNumbers.get(0).isDeleted()).isEqualTo(true);
    }
}