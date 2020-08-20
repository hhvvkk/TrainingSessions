package com.example.tdd.todos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PunctuationTest {

    //TODO
    // - single quote '
    // - then quotations "
    // - then curly bracket {} and []
    // -- The curly and block bracket should change the dynamic quite a bit
    // -

    private final Punctuation validPunctuationStringCheck = new Punctuation();

    @Test
    public void shouldReturnTrueForEmptyString() {
        String emptyString = "";
        Assertions.assertTrue(validPunctuationStringCheck.hasWellFormedQuotations(emptyString));
    }

    @Test
    public void shouldReturnTrueForValidSingleQuotes() {
        Assertions.assertTrue(
                validPunctuationStringCheck.hasWellFormedQuotations(
                        "A string 'with a valid beginning and end' parenthesis"
                )
        );
    }

    @Test
    public void shouldReturnFalseForInValidSingleQuotes() {
        Assertions.assertFalse(
                validPunctuationStringCheck.hasWellFormedQuotations(
                        "A string 'with a valid beginning and end parenthesis"
                )
        );
    }

    @Test
    public void shouldReturnTrueForValidQuotation() {
        Assertions.assertTrue(
                validPunctuationStringCheck.hasWellFormedQuotations(
                        "A string \"with a valid beginning and end\" parenthesis"
                )
        );
    }

    @Test
    public void shouldReturnFalseForInValidQuotation() {
        Assertions.assertFalse(
                validPunctuationStringCheck.hasWellFormedQuotations(
                        "A string \"with a beginning parenthesis"
                )
        );
    }

}
