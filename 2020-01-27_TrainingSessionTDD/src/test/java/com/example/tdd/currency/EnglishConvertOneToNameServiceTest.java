package com.example.tdd.currency;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EnglishConvertOneToNameServiceTest {

    private EnglishConvertOneToNameService englishConvertOneToNameService
            = new EnglishConvertOneToNameService();

    @Test
    public void shouldReturnValidStringAsNumber() {
        String one = "one";

        String result = englishConvertOneToNameService.convertToString(1);
        Assertions.assertEquals(one, result);
    }

}
