package com.DynamicProgramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class LongestValidParenthesesTest {

    @Test
    void testLongestValidParentheses() {
        Assertions.assertEquals(0, LongestValidParentheses.getLongestValidParentheses(null));
        Assertions.assertEquals(0, LongestValidParentheses.getLongestValidParentheses(""));
        Assertions.assertEquals(2, LongestValidParentheses.getLongestValidParentheses("())"));
        Assertions.assertEquals(2, LongestValidParentheses.getLongestValidParentheses("()("));
        Assertions.assertEquals(2, LongestValidParentheses.getLongestValidParentheses("(()"));
        Assertions.assertEquals(4, LongestValidParentheses.getLongestValidParentheses("(())"));
        Assertions.assertEquals(2, LongestValidParentheses.getLongestValidParentheses(")())"));
    }

}
