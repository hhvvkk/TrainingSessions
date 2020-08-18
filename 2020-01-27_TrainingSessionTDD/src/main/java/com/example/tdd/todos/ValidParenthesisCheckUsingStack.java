package com.example.tdd.todos;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ValidParenthesisCheckUsingStack {


    /*
    This problem was asked by Facebook.

    Given a string of round, curly, and square open and closing brackets, return whether the brackets are balanced (well-formed).

    For example, given the string "([])[]({})", you should return true.

    Given the string "([)]" or "((()", you should return false.
     */

    private List<Character> beginParentheses = Arrays.asList(
            '{', '('
    );

    private List<Character> endParentheses = Arrays.asList(
            '}', ')'
    );

    private boolean isValidParentheses(char[] characters) {

        Stack<Character> characterStack = new Stack<>();

        for (char character : characters) {
            if (beginParentheses.contains(character)) {
                characterStack.add(character);
            } else if (endParentheses.contains(character)) {
                if (characterStack.isEmpty()) {
                    return false;
                }

                char charFromStack = characterStack.pop();

                int indexOfBeginningParentheses = beginParentheses.indexOf(charFromStack);
                int indexOfEndParentheses = endParentheses.indexOf(character);

                if (indexOfBeginningParentheses != indexOfEndParentheses) {
                    return false;
                }
            }
        }

        return true;
    }

    private void run() {
        String expression1 = "()";
        String expression2 = "())";
        String expression3 = "{{()}}";
        String expression4 = "{{))}}";
        String expression5 = "{{()()}}";

        System.out.println("expression1 " + expression1 + "  = " + isValidParentheses(expression1.toCharArray()));
        System.out.println("expression2  " + expression2 + " = " + isValidParentheses(expression2.toCharArray()));
        System.out.println("expression3  " + expression3 + " = " + isValidParentheses(expression3.toCharArray()));
        System.out.println("expression4 " + expression4 + " = " + isValidParentheses(expression4.toCharArray()));
        System.out.println("expression5 " + expression5 + " = " + isValidParentheses(expression5.toCharArray()));

    }

    public static void main(String[] args) {
        ValidParenthesisCheckUsingStack r = new ValidParenthesisCheckUsingStack();
        r.run();
    }
}
