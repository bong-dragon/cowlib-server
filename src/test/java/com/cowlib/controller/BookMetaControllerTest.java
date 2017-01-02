package com.cowlib.controller;

import com.Application;
import com.cowlib.model.BookMetaSearchResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("local")
public class BookMetaControllerTest {
private static final Logger LOGGER = LoggerFactory.getLogger(BookMetaControllerTest.class);

    private MockMvc mockMvc;

    @Autowired
    BookMetaController bookMetaController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(bookMetaController).build();
    }

    @Test
    public void search() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/v1/bookMetas/search")
                .param("q", "책")
                .param("pageno", "1"))
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        BookMetaSearchResult bookMetaSearchResult = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), BookMetaSearchResult.class);
        LOGGER.info("b={}", bookMetaSearchResult);

        assertThat(bookMetaSearchResult.getBookMetas()).isNotEmpty();
        assertThat(bookMetaSearchResult.getTotalCount()).isGreaterThan(0);
    }


    @Test(expected = NestedServletException.class)
    public void search_page_no_over3() throws Exception {
        mockMvc.perform(get("/v1/bookMetas/search")
                .param("q", "책")
                .param("pageno", "4"))
                .andExpect(status().isOk())
                .andReturn();
    }

}