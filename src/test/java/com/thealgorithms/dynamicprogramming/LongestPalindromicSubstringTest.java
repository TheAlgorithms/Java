package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LongestPalindromicSubstringTest {

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
            Arguments.of("babad", "aba"), Arguments.of("cbbd", "bb"), Arguments.of("a", "a"), Arguments.of("x", "x"), Arguments.of("", ""), Arguments.of("aaaa", "aaaa"), Arguments.of("mm", "mm"), Arguments.of("level", "level"), Arguments.of("bananas", "anana"), Arguments.of("abacabad", "abacaba"));
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    public void testLps(String input, String expected) {
        assertEquals(expected, LongestPalindromicSubstring.lps(input));
    }
}
