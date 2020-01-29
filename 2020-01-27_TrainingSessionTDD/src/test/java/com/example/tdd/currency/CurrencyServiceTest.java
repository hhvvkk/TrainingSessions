package com.example.tdd.currency;

import com.example.tdd.currency.entity.Currency;
import com.example.tdd.currency.entity.CurrencyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

//Contract testing
@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTest {

    @Mock
    private static CurrencyRepository currencyRepository;

    @Mock
    private static EnglishConvertNumberToNameService englishConvertNumberToNameService;

    @InjectMocks
    private static CurrencyService currencyService;

    @Test
    public void shouldReturnCurrencyCreated() {
        CurrencyType RAND_CURRENCY = CurrencyType.RAND;
        String fifteenName = "fifteen";
        int fifteenInt = 15;

        Mockito
                .when(englishConvertNumberToNameService.convertToString(Mockito.anyInt()))
                .thenReturn(fifteenName);

        Mockito
                .lenient()
                .when(currencyRepository.save(Mockito.any()))
                .thenAnswer(AdditionalAnswers.returnsFirstArg());


        Currency currency = currencyService.save(RAND_CURRENCY, fifteenInt);

        Assertions.assertEquals(RAND_CURRENCY, currency.getType());
        Assertions.assertEquals(fifteenName, currency.getEnglishNumberName());
        Assertions.assertEquals(fifteenInt, currency.getValue());
    }

    @Test
    public void shouldThrowExceptionOnEmptyCurrency() {
        Assertions.assertThrows(ResponseStatusException.class, () -> currencyService.save(null, 34));
    }

}
