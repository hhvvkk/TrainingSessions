package com.example.tdd.currency;

import com.example.tdd.currency.dto.CurrencyDTO;
import com.example.tdd.currency.entity.CurrencyType;
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
public class CurrencyIntegrationTesting {

    //Test database configuration H2
    // https://stackoverflow.com/questions/32001391/configure-specific-in-memory-database-for-testing-purpose-in-spring/55862900#55862900
    // Run self contained in-memory tests
    // https://www.baeldung.com/spring-jpa-test-in-memory-database

    // TODO currently we have two application.properties - 1) main/resources & 2) test/resources
    // HHH000400: Using dialect: org.hibernate.dialect.H2Dialect

    @Autowired
    private MockMvc mockMvc;

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
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.isA(Integer.class)))
                .andExpect(jsonPath("$.englishNumberName", Matchers.is("twenty")));
//        .andExpect(jsonPath("$.orderId", is(DEFAULT_ORDER_ID)));
    }


    @Test
    public void shouldCreateCurrencyAndBeStoredInDatabase() throws Exception {
        CurrencyDTO poundCurrency = new CurrencyDTO();
        poundCurrency.setValue(20);
        poundCurrency.setType(CurrencyType.POUND);

        CurrencyDTO euroCurrency = new CurrencyDTO();
        euroCurrency.setValue(15);
        euroCurrency.setType(CurrencyType.EUROS);


        String poundCurrencyAsString = mapper.writeValueAsString(poundCurrency);
        String euroCurrencyAsString = mapper.writeValueAsString(poundCurrency);

        MockHttpServletRequestBuilder firstCurrencyCreateHttp =
                post("/currency")
                        .content(poundCurrencyAsString)
                        .header("content-type", "application/json");


        MockHttpServletRequestBuilder secondCurrencyCreateHttp =
                post("/currency")
                        .content(euroCurrencyAsString)
                        .header("content-type", "application/json");


        this.mockMvc.perform(firstCurrencyCreateHttp);
        this.mockMvc.perform(secondCurrencyCreateHttp);


        MockHttpServletRequestBuilder getCurrenciesStoredHttp =
                get("/currency")
                        .header("content-type", "application/json");

        this.mockMvc
                .perform(getCurrenciesStoredHttp)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    public void shouldThrow401OnBadCurrencyNumber() throws Exception {
        CurrencyDTO currencyToCreate = new CurrencyDTO();
        currencyToCreate.setValue(20);
        //Type being set as null...
        currencyToCreate.setType(null);

        String contentAsString = mapper.writeValueAsString(currencyToCreate);

        MockHttpServletRequestBuilder mockHttpBuilt =
                post("/currency")
                        .content(contentAsString)
                        .header("content-type", "application/json");

        this.mockMvc.perform(mockHttpBuilt)
                .andExpect(status().is4xxClientError());
    }

}
