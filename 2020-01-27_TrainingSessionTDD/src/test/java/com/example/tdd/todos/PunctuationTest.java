package com.example.tdd.todos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class PunctuationTest {

    //TODO
    // - single quote '
    // - then quotations "
    // - then curly bracket {} and []

    @Test
    public void shouldReturnTrueForEmptyString() {
        String emptyString = "";
        Assertions.assertTrue(Punctuation.wellFormed(emptyString));
    }

    @Test
    public void shouldReturnTrueForValidSingleNoPunctuation() {
        Assertions.assertTrue(
                Punctuation.wellFormed(
                        "A string with no punctuation "
                )
        );
    }


    @Test
    public void shouldReturnTrueForValidSingleQuotes() {
        Assertions.assertTrue(
                Punctuation.wellFormed(
                        "A string 'with a valid beginning and end' "
                )
        );
    }

    @Test
    public void shouldReturnFalseForInValidSingleQuotes() {
        Assertions.assertFalse(
                Punctuation.wellFormed(
                        "A string 'with a valid beginning and end "
                )
        );
    }

    @Test
    public void shouldReturnTrueForValidQuotation() {
        Assertions.assertTrue(
                Punctuation.wellFormed(
                        "A string \"with a valid beginning and end\" "
                )
        );
    }

    @Test
    public void shouldReturnFalseForInValidQuotation() {
        Assertions.assertFalse(
                Punctuation.wellFormed(
                        "A string \"with a beginning"
                )
        );
    }


    @Test
    public void shouldReturnFalseForInvalidMixQuotations() {
        Assertions.assertFalse(
                Punctuation.wellFormed(
                        "Should return false for 'Invalid \" mix of punctuation ' \" used"
                )
        );
    }

    @Test
    public void shouldReturnTrueForValidMixQutations() {
        Assertions.assertTrue(
                Punctuation.wellFormed(
                        "Should return false for Valid \" mix of ' punctuation ' \" used"
                )
        );
    }

    @Test
    public void shouldReturnTrueForValidCurlyBracket() {
        Assertions.assertTrue(
                Punctuation.wellFormed(
                        "A string {with a valid beginning and end}"
                )
        );
    }

    @Test
    public void shouldReturnFalseForInValidCurlyBracket() {
        Assertions.assertFalse(
                Punctuation.wellFormed(
                        "A string {with a beginning"
                )
        );

        Assertions.assertFalse(
                Punctuation.wellFormed(
                        "A string with an end}"
                )
        );
    }


    @Test
    public void shouldReturnTrueForValidSquareBracket() {
        Assertions.assertTrue(
                Punctuation.wellFormed(
                        "A string [with a valid beginning and end]"
                )
        );
    }

    @Test
    public void shouldReturnFalseForInValidSquareBracket() {
        Assertions.assertFalse(
                Punctuation.wellFormed(
                        "A string [with a beginning"
                )
        );

        Assertions.assertFalse(
                Punctuation.wellFormed(
                        "A string with an end]"
                )
        );
    }

    @Test
    public void testMultiple() {
        List<String> arrayOfValidStrings = Arrays.asList(
        );

        //TODO
    }


}
