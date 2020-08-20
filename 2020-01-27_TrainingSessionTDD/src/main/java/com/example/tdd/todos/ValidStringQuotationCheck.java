package com.example.tdd.todos;

/**
 * A checker class to see whether a string has a well formed beginning and en parenthesis
 * - Beginning must be ended by a valid and similar character
 * - This checker must check :
 * -- ''
 * -- ""
 * -- []
 * -- {}
 */
public class ValidStringQuotationCheck {
    public boolean hasWellFormedQuotations(String stringToCheck) {
        int numberOfSingleQuotes = 0;
        int numberOfQuotation = 0;

        for (char character: stringToCheck.toCharArray()) {
            if (character == '\'') {
                numberOfSingleQuotes ++;
            } else if (character == '\"') {
                numberOfQuotation++;
            }
        }

        return numberOfSingleQuotes % 2 == 0
                && numberOfQuotation % 2 == 0;
    }
}
