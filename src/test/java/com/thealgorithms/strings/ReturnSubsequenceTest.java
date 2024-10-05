package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ReturnSubsequenceTest {

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testSubsequences(String input, String[] expected) {
        String[] actual = ReturnSubsequence.getSubsequences(input);
        assertArrayEquals(expected, actual);
    }

    static Stream<Arguments> provideTestCases() {
        return Stream.of(Arguments.of("", new String[] {""}), Arguments.of("a", new String[] {"", "a"}), Arguments.of("ab", new String[] {"", "b", "a", "ab"}), Arguments.of("abc", new String[] {"", "c", "b", "bc", "a", "ac", "ab", "abc"}),
            Arguments.of("aab", new String[] {"", "b", "a", "ab", "a", "ab", "aa", "aab"}));
    }
}
