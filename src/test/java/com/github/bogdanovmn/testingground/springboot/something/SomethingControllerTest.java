package com.github.bogdanovmn.testingground.springboot.something;

import com.github.bogdanovmn.testingground.springboot.inmemorystat.InMemoryStatistic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SomethingController.class)
@ContextConfiguration(classes = SomethingController.class)
@AutoConfigureMockMvc(addFilters = false)
public class SomethingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SomethingService somethingService;
    @MockBean
    private InMemoryStatistic inMemoryStatistic;

    @Test
    void getPage() throws Exception {
        when(somethingService.getPage(1, 3)).thenReturn(Page.empty());
        mockMvc.perform(
            get("/somethings")
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("page", "1")
        ).andExpect(status().isOk());
    }
}