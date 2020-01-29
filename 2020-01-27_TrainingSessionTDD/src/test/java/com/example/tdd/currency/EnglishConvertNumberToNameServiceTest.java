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
        otherNumbers.put(21, "twenty one");
        otherNumbers.put(34, "thirty four");
        otherNumbers.put(99, "ninety nine");
        otherNumbers.put(105, "one hundred five");
        otherNumbers.put(100, "one hundred");
        otherNumbers.put(202, "two hundred two");
        otherNumbers.put(300, "three hundred");
        otherNumbers.put(507, "five hundred seven");
        otherNumbers.put(998, "nine hundred ninety eight");
        otherNumbers.put(999, "nine hundred ninety nine");
    }

    @Test
    public void shouldReturnValidStringAsNumber() {
        for (int key : otherNumbers.keySet()) {
            String result = englishNumberNameService.convertToString(key);
            Assertions.assertEquals(otherNumbers.get(key), result);
        }
    }
}
