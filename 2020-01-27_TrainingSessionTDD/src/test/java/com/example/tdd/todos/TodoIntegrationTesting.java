package com.example.tdd.todos;

import com.example.tdd.todos.dto.TodoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @ActiveProfiles("testDatabaseProfile")
// @WebMvcTest
// @AutoConfigureWebMvc

@ExtendWith({SpringExtension.class, MockitoExtension.class}) //OLD one was RUN WITH!! -- @RunsWith
@SpringBootTest // --->  With the @SpringBootTest annotation, Spring Boot provides a convenient way to start up an application context to be used in a test.
@AutoConfigureMockMvc // --->  With the @SpringBootTest annotation, Spring Boot provides a convenient way to start up an application context to be used in a test.
public class TodoIntegrationTesting {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldSaveTodoInDatabaseAndHaveValidPunctuation() throws Exception {
        final String TODO_TEXT = "This is a todo";
        TodoDTO dto = new TodoDTO();
        dto.setText(TODO_TEXT);

        String contentAsString = mapper.writeValueAsString(dto); // Mapped to JSON-String

        MockHttpServletRequestBuilder mockHttpBuilt =
                post("/todo")
                        .content(contentAsString)
                        .header("content-type", "application/json");

        this.mockMvc.perform(mockHttpBuilt)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.isA(Integer.class)))
                .andExpect(jsonPath("$.text", Matchers.is(TODO_TEXT)))
                .andExpect(jsonPath("$.valid", Matchers.is(true)));
    }

    @Test
    public void shouldCreateManyTodosAndBeStoredInDatabase() throws Exception {
        final String FIRST_TODO_TEXT = "Should go to mall";
        final String SECOND_TODO_TEXT = "Should have a 'bath'";
        final String THIRD_TODO_TEXT = "Finish off '2020";

        TodoDTO firstTODO = new TodoDTO();
        firstTODO.setText(FIRST_TODO_TEXT);
        TodoDTO secondTODO = new TodoDTO();
        secondTODO.setText(SECOND_TODO_TEXT);
        TodoDTO thirdTODO = new TodoDTO();
        thirdTODO.setText(THIRD_TODO_TEXT);

        MockHttpServletRequestBuilder firstCreateHttp = post("/todo")
                .content(mapper.writeValueAsString(firstTODO))
                .header("content-type", "application/json");

        MockHttpServletRequestBuilder secondCreateHttp = post("/todo")
                .content(mapper.writeValueAsString(secondTODO))
                .header("content-type", "application/json");

        MockHttpServletRequestBuilder thirdCreateHttp = post("/todo")
                .content(mapper.writeValueAsString(thirdTODO))
                .header("content-type", "application/json");

        MvcResult resultFirst = this.mockMvc.perform(firstCreateHttp)
                .andExpect(jsonPath("$.id", Matchers.isA(Integer.class)))
                .andExpect(jsonPath("$.text", Matchers.is(FIRST_TODO_TEXT)))
                .andExpect(jsonPath("$.valid", Matchers.is(true)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        MvcResult resultSecond = this.mockMvc.perform(secondCreateHttp)
                .andExpect(jsonPath("$.valid", Matchers.is(true)))
                .andReturn();
        MvcResult resultThird = this.mockMvc.perform(thirdCreateHttp)
                .andExpect(jsonPath("$.valid", Matchers.is(false)))
                .andReturn();

        Integer firstTodoId = JsonPath.read(resultFirst.getResponse().getContentAsString(), "$.id");
        Integer secondTodoId = JsonPath.read(resultSecond.getResponse().getContentAsString(), "$.id");
        Integer thirdTodoId = JsonPath.read(resultThird.getResponse().getContentAsString(), "$.id");

        MockHttpServletRequestBuilder getFirstTodoByIdHttp = get(String.format("/todo/%d", firstTodoId))
                .header("content-type", "application/json");
        MockHttpServletRequestBuilder getSecondTodoByIdHttp = get(String.format("/todo/%d", secondTodoId))
                .header("content-type", "application/json");
        MockHttpServletRequestBuilder getThirdTodoByIdHttp = get(String.format("/todo/%d", thirdTodoId))
                .header("content-type", "application/json");

        this.mockMvc
                .perform(getFirstTodoByIdHttp)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text", Matchers.is(FIRST_TODO_TEXT)));

        this.mockMvc
                .perform(getSecondTodoByIdHttp)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text", Matchers.is(SECOND_TODO_TEXT)));

        this.mockMvc
                .perform(getThirdTodoByIdHttp)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text", Matchers.is(THIRD_TODO_TEXT)));
    }

    @Test
    public void shouldThrowBadRequestWhenInvalidTodoId() throws Exception {
        MockHttpServletRequestBuilder mockHttpBuilt =
                get("/todo/-1")
                        .header("content-type", "application/json");

        this.mockMvc
                .perform(mockHttpBuilt)
                .andExpect(
                        status().is4xxClientError()
                )
        .andDo(MockMvcResultHandlers.print());
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
