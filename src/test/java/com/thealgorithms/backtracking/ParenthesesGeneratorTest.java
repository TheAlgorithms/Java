package com.thealgorithms.backtracking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ParenthesesGeneratorTest {

    @Test
    void testGenerateParenthesesWithZeroPairs() {
        ParenthesesGenerator generator = new ParenthesesGenerator();
        List<String> result = generator.generateParentheses(0);
        assertEquals(List.of(""), result);
    }

    @Test
    void testGenerateParenthesesWithOnePair() {
        ParenthesesGenerator generator = new ParenthesesGenerator();
        List<String> result = generator.generateParentheses(1);
        assertEquals(List.of("()"), result);
    }

    @Test
    void testGenerateParenthesesWithTwoPairs() {
        ParenthesesGenerator generator = new ParenthesesGenerator();
        List<String> result = generator.generateParentheses(2);
        assertEquals(List.of("(())", "()()"), result);
    }

    @Test
    void testGenerateParenthesesWithThreePairs() {
        ParenthesesGenerator generator = new ParenthesesGenerator();
        List<String> result = generator.generateParentheses(3);
        assertEquals(List.of("((()))", "(()())", "(())()", "()(())", "()()()"), result);
    }

    @Test
    void testGenerateParenthesesWithFourPairs() {
        ParenthesesGenerator generator = new ParenthesesGenerator();
        List<String> result = generator.generateParentheses(4);
        assertEquals(List.of("(((())))", "((()()))", "((())())", "((()))()", "(()(()))", "(()()())", "(()())()", "(())(())", "(())()()", "()((()))", "()(()())", "()(())()", "()()(())", "()()()()"), result);
    }
}
