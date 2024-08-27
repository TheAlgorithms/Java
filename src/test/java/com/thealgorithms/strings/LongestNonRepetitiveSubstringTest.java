package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LongestNonRepetitiveSubstringTest {

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(Arguments.of("", 0), Arguments.of("a", 1), Arguments.of("abcde", 5), Arguments.of("aaaaa", 1), Arguments.of("abca", 3), Arguments.of("abcdeabc", 5), Arguments.of("a1b2c3", 6), Arguments.of("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 62),
            Arguments.of("aabb", 2), Arguments.of("abcdefghijabc", 10));
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testLengthOfLongestSubstring(String input, int expectedLength) {
        assertEquals(expectedLength, LongestNonRepetitiveSubstring.lengthOfLongestSubstring(input));
    }
}
