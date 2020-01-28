package com.example.tdd.currency;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EnglishNumberNameServiceTest {

    private EnglishNumberNameService englishNumberNameService
            = new EnglishNumberNameService();

    @Test
    public void shouldReturnValidStringAsNumber() {
        String one = "one";

        String result = englishNumberNameService.convertToString(1);
        Assertions.assertEquals(one, result);
    }

}
