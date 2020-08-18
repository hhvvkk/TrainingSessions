package com.example.tdd.todos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidStringQuotationCheckTest {

    //TODO
    // - single quote '
    // - then quotations "
    // - then curly bracket {} and []
    // -- The curly and block bracket should change the dynamic quite a bit
    // -

    private final ValidStringQuotationCheck validQuotationStringCheck = new ValidStringQuotationCheck();

    @Test
    public void shouldReturnTrueForEmptyString() {
        String emptyString = "";
        Assertions.assertTrue(validQuotationStringCheck.hasWellFormedQuotations(emptyString));
    }

    @Test
    public void shouldReturnTrueForValidValidSingleQuotes() {
        String validSingleQuote = "A string 'with a valid beginning and end' parenthesis";
        String validSingleQuote2 = "A string 'with a valid beginning and end' parenthesis";
        String validSingleQuote3 = "A string 'with a valid beginning and end' parenthesis";

        Assertions.assertTrue(validQuotationStringCheck.hasWellFormedQuotations(validSingleQuote));
        Assertions.assertTrue(validQuotationStringCheck.hasWellFormedQuotations(validSingleQuote2));
        Assertions.assertTrue(validQuotationStringCheck.hasWellFormedQuotations(validSingleQuote3));
    }

}
