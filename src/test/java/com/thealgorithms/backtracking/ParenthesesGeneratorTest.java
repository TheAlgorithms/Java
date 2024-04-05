package com.thealgorithms.backtracking;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
public class ParenthesesGeneratorTest {
    @ParameterizedTest
    @MethodSource("tcStream")
    void numberTests(int input, List<String> expected) {
        assertEquals(expected, ParenthesesGenerator.generateParentheses(input));
    }
    private static Stream<Arguments> tcStream() {
        return Stream.of(Arguments.of(0, List.of("")), Arguments.of(1, List.of("()")), Arguments.of(2, List.of("(())", "()()")), Arguments.of(3, List.of("((()))", "(()())", "(())()", "()(())", "()()()")),
            Arguments.of(4, List.of("(((())))", "((()()))", "((())())", "((()))()", "(()(()))", "(()()())", "(()())()", "(())(())", "(())()()", "()((()))", "()(()())", "()(())()", "()()(())", "()()()()")));
    }
}
