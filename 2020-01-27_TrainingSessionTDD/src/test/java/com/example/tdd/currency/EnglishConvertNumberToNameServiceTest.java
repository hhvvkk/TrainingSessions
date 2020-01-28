package com.example.tdd.currency;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class EnglishConvertNumberToNameServiceTest {

    private EnglishConvertNumberToNameService englishNumberNameService
            = new EnglishConvertNumberToNameService();

    private static final Map<Integer, String> otherNumbers = new HashMap<>();

    @BeforeAll
    static void init() {
        otherNumbers.put(1, "one");
        otherNumbers.put(2, "two");
        otherNumbers.put(5, "five");
        otherNumbers.put(9, "nine");
        otherNumbers.put(18, "eighteen");
        otherNumbers.put(19, "nineteen");
    }

    @Test
    public void shouldReturnValidStringAsNumber() {
        for (int key : otherNumbers.keySet()) {
            String result = englishNumberNameService.convertToString(key);
            Assertions.assertEquals(otherNumbers.get(key), result);
        }
    }
}
