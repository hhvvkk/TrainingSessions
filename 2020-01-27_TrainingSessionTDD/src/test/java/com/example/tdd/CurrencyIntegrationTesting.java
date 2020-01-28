package com.example.tdd;

import com.example.tdd.currency.CurrencyRepository;
import com.example.tdd.currency.dto.CurrencyDTO;
import com.example.tdd.currency.entity.CurrencyType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class CurrencyIntegrationTesting {

    //Test database configuration H2
    //https://stackoverflow.com/questions/32001391/configure-specific-in-memory-database-for-testing-purpose-in-spring/55862900#55862900

    @Autowired
    private MockMvc mockMvc;

    @Mock
    CurrencyRepository currencyRepository;

    @BeforeEach
    public void init() {
        Mockito
                .lenient()
                .when(currencyRepository.save(Mockito.any()))
                .thenAnswer(AdditionalAnswers.returnsFirstArg());
    }


    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldSaveCurrencyInDatabaseAndReturnOKResponse() throws Exception {
        CurrencyDTO currencyToCreate = new CurrencyDTO();
        currencyToCreate.setValue(20);
        currencyToCreate.setType(CurrencyType.POUND);

        String contentAsString = mapper.writeValueAsString(currencyToCreate);

        MockHttpServletRequestBuilder mockHttpBuilt =
                post("/currency")
                .content(contentAsString)
                .header("content-type", "application/json");

        this.mockMvc.perform(mockHttpBuilt)
                .andExpect(status().isOk());

    }

}
