package com.thealgorithms.dynamicprogramming;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LongestValidParenthesesTest {

    LongestValidParentheses longestValidParentheses = new LongestValidParentheses();

    @Test
    void shouldReturnZeroWhenSingleOpeningParenthesisIsGiven() {
        String input = "(";
        int validLength = longestValidParentheses.getLongestValidParentheses(input);
        assertEquals(0, validLength);
    }

    @Test
    void shouldReturnZeroWhenSingleClosingParenthesisIsGiven() {
        String input = ")";
        int validLength = longestValidParentheses.getLongestValidParentheses(input);
        assertEquals(0, validLength);
    }

    @Test
    void shouldReturnZeroWhenNullStringIsGiven() {
        String input = "";
        int validLength = longestValidParentheses.getLongestValidParentheses(input);
        assertEquals(0, validLength);
    }

    @Test
    void shouldReturnTwoWhenTwoBalancedParenthesesAreGiven() {
        String input = "()";
        int validLength = longestValidParentheses.getLongestValidParentheses(input);
        assertEquals(2, validLength);
    }

    @Test
    void shouldReturnLengthWhenInputStringIsValid() {
        String input = "()((()))";
        int validLength = longestValidParentheses.getLongestValidParentheses(input);
        assertEquals(8, validLength);
    }

    @Test
    void shouldReturnValidLengthWhenInputStringIsGiven() {
        String input = "((()((())))";
        int validLength = longestValidParentheses.getLongestValidParentheses(input);
        assertEquals(10, validLength);
    }
}
