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
        Assertions.assertTrue(validPunctuationStringCheck.wellFormed(emptyString));
    }

    @Test
    public void shouldReturnTrueForValidSingleNoPunctuation() {
        Assertions.assertTrue(
                validPunctuationStringCheck.wellFormed(
                        "A string with no punctuation "
                )
        );
    }


    @Test
    public void shouldReturnTrueForValidSingleQuotes() {
        Assertions.assertTrue(
                validPunctuationStringCheck.wellFormed(
                        "A string 'with a valid beginning and end' "
                )
        );
    }

    @Test
    public void shouldReturnFalseForInValidSingleQuotes() {
        Assertions.assertFalse(
                validPunctuationStringCheck.wellFormed(
                        "A string 'with a valid beginning and end "
                )
        );
    }

    @Test
    public void shouldReturnTrueForValidQuotation() {
        Assertions.assertTrue(
                validPunctuationStringCheck.wellFormed(
                        "A string \"with a valid beginning and end\" "
                )
        );
    }

    @Test
    public void shouldReturnFalseForInValidQuotation() {
        Assertions.assertFalse(
                validPunctuationStringCheck.wellFormed(
                        "A string \"with a beginning"
                )
        );
    }

    @Test
    public void shouldReturnTrueForValidCurlyBracket() {
        Assertions.assertTrue(
                validPunctuationStringCheck.wellFormed(
                        "A string {with a valid beginning and end}"
                )
        );
    }

    @Test
    public void shouldReturnFalseForInValidCurlyBracket() {
        Assertions.assertFalse(
                validPunctuationStringCheck.wellFormed(
                        "A string {with a beginning"
                )
        );

        Assertions.assertFalse(
                validPunctuationStringCheck.wellFormed(
                        "A string with an end}"
                )
        );
    }


}
