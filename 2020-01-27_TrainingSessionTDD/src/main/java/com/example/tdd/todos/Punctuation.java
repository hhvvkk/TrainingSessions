package com.example.tdd.todos;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * A checker class to see whether a string has a well formed beginning and en parenthesis
 * - Beginning must be ended by a valid and similar character
 * - This checker must check :
 * -- ''
 * -- ""
 * -- []
 * -- {}
 */
public class Punctuation {

    private List<Character> begin = Arrays.asList(
            '\'', '\"', '{', '['
    );

    private List<Character> end = Arrays.asList(
            '\'', '\"', '}', ']'
    );

    public boolean wellFormed(String input) {
        Stack<Character> punctuationStack = new Stack<>();

        for (char character: input.toCharArray()){
            if (end.contains(character)
                    && !punctuationStack.isEmpty()
                    && punctuationStack.peek().equals(character)) {
                punctuationStack.pop();
                continue;
            }

            if (begin.contains(character)) {
                punctuationStack.add(character);
            } else if (end.contains(character)) {
                if (punctuationStack.isEmpty()) {
                    return false;
                }

                char charFromStack = punctuationStack.pop();

                int indexBeginning = begin.indexOf(charFromStack);
                int indexEnd = end.indexOf(character);

                if (indexBeginning != indexEnd) {
                    return false;
                }
            }
        }

        return punctuationStack.isEmpty();
    }
}
