package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LongestPalindromicSubstringTest {

    @ParameterizedTest
    @MethodSource("provideTestCasesForLongestPalindrome")
    void testLongestPalindrome(String input, String expected) {
        assertEquals(expected, LongestPalindromicSubstring.longestPalindrome(input));
    }

    private static Stream<Arguments> provideTestCasesForLongestPalindrome() {
        return Stream.of(Arguments.of("babad", "bab"), Arguments.of("cbbd", "bb"), Arguments.of("a", "a"), Arguments.of("", ""), Arguments.of("abc", "a"), Arguments.of(null, ""), Arguments.of("aaaaa", "aaaaa"));
    }
}
