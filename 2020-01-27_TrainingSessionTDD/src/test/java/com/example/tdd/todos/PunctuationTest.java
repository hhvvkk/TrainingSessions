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

    public static final List<String> IN_VALID_STRINGS = Arrays.asList(
            "We have a String with beginning ' and end quotes",
            "We have a String with beginning ' ' 'and end quotes",
            "A String with beginning \" and end double quotes",
            "A String with beginning \" \" \" and end double quotes",
            "A String with beginning \" ' in between \" 'and end double quotes",
            "A string with curly beginning { no end",
            "A string with curly end } no beginning",
            "A string with block end [ no beginning",
            "A string with curly end ] no beginning"
    );

    public static final List<String> VALID_STRINGS = Arrays.asList(
            "We have a String with beginning ' and end ' quotes",
            "A String with beginning \" and end \" double quotes",
            "",
            "A String that has single 'quote' and double \"quotes\"",
            "A string with curly beginning { and } end",
            "A string with curly beginning [ and ] end",
            "A string with beginning [ {and} ] end",
            "A string with beginning \"[' {and}' ]\" end"
    );

    @Test
    public void shouldReturnTrueForValidPunctuation() {
        for (String toTest: VALID_STRINGS){
            Assertions.assertTrue(Punctuation.wellFormed(toTest));
        }
    }

    @Test
    public void shouldReturnFalseForInValidPunctuation() {
        for (String toTest: IN_VALID_STRINGS){
            Assertions.assertFalse(Punctuation.wellFormed(toTest));
        }
    }


}
