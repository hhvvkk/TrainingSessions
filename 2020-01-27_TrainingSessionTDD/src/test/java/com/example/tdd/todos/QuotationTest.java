package com.example.tdd.todos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuotationTest {

    //TODO
    // - single quote '
    // - then quotations "
    // - then curly bracket {} and []
    // -- The curly and block bracket should change the dynamic quite a bit
    // -

    private final Quotation validQuotationStringCheck = new Quotation();

    @Test
    public void shouldReturnTrueForEmptyString() {
        String emptyString = "";
        Assertions.assertTrue(validQuotationStringCheck.hasWellFormedQuotations(emptyString));
    }

    @Test
    public void shouldReturnTrueForValidSingleQuotes() {
        Assertions.assertTrue(
                validQuotationStringCheck.hasWellFormedQuotations(
                        "A string 'with a valid beginning and end' parenthesis"
                )
        );
    }

    @Test
    public void shouldReturnFalseForInValidSingleQuotes() {
        Assertions.assertFalse(
                validQuotationStringCheck.hasWellFormedQuotations(
                        "A string 'with a valid beginning and end parenthesis"
                )
        );
    }

    @Test
    public void shouldReturnTrueForValidQuotation() {
        Assertions.assertTrue(
                validQuotationStringCheck.hasWellFormedQuotations(
                        "A string \"with a valid beginning and end\" parenthesis"
                )
        );
    }

    @Test
    public void shouldReturnFalseForInValidQuotation() {
        Assertions.assertFalse(
                validQuotationStringCheck.hasWellFormedQuotations(
                        "A string \"with a beginning parenthesis"
                )
        );
    }

}
