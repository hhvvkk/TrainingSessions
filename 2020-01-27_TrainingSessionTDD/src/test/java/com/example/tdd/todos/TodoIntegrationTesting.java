package com.example.tdd.todos;


import com.example.tdd.todos.dto.TodoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, MockitoExtension.class}) //OLD one was RUN WITH!!
@SpringBootTest
@AutoConfigureMockMvc
public class TodoIntegrationTesting {


    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldSaveTodoInDatabaseAndHaveValidPunctuation() throws Exception {
        final String TODO_TEXT = "This is a todo";
        TodoDTO dto = new TodoDTO();
        dto.setText(TODO_TEXT);

        String contentAsString = mapper.writeValueAsString(dto);

        MockHttpServletRequestBuilder mockHttpBuilt =
                post("/todo")
                        .content(contentAsString)
                        .header("content-type", "application/json");

        this.mockMvc.perform(mockHttpBuilt)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.isA(Integer.class)))
                .andExpect(jsonPath("$.text", Matchers.is(TODO_TEXT)));
//        .andExpect(jsonPath("$.orderId", is(DEFAULT_ORDER_ID)));
    }


    @Test
    public void shouldCreateCurrencyAndBeStoredInDatabase() throws Exception {
        final String TODO_TEXT_SHOULD_GO_TO_MALL = "Should go to mall";
        TodoDTO firstTODO = new TodoDTO();
        firstTODO.setText(TODO_TEXT_SHOULD_GO_TO_MALL);

        TodoDTO secondTODO = new TodoDTO();
        secondTODO.setText(TODO_TEXT_SHOULD_GO_TO_MALL);

        String firstTodoAsString = mapper.writeValueAsString(firstTODO);
        String secondTodoAsString = mapper.writeValueAsString(secondTODO);

        MockHttpServletRequestBuilder firstCurrencyCreateHttp = post("/todo")
                .content(firstTodoAsString)
                .header("content-type", "application/json");


        MockHttpServletRequestBuilder secondCurrencyCreateHttp = post("/todo")
                .content(secondTodoAsString)
                .header("content-type", "application/json");

        this.mockMvc.perform(firstCurrencyCreateHttp);
        this.mockMvc.perform(secondCurrencyCreateHttp);


        MockHttpServletRequestBuilder getHttpRequest = get("/todo")
                .header("content-type", "application/json");

        this.mockMvc
                .perform(getHttpRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    public void shouldThrow400InBadTodoText() throws Exception {
        TodoDTO dto = new TodoDTO();
        dto.setText("");

        String contentAsString = mapper.writeValueAsString(dto);

        MockHttpServletRequestBuilder mockHttpBuilt =
                post("/todo")
                        .content(contentAsString)
                        .header("content-type", "application/json");

        this.mockMvc
                .perform(mockHttpBuilt)
                .andExpect(
                        status().is4xxClientError()
                );
    }
}
