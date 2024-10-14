package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class AbbreviationTest {

    @ParameterizedTest
    @MethodSource("provideTestCases")
    public void testAbbreviation(String a, String b, boolean expected) {
        assertEquals(expected, Abbreviation.abbr(a, b));
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
            // Example test case from problem description
            Arguments.of("daBcd", "ABC", Boolean.TRUE),

            // Test case where transformation is impossible
            Arguments.of("dBcd", "ABC", Boolean.FALSE),

            // Test case with exact match (all uppercase)
            Arguments.of("ABC", "ABC", Boolean.TRUE),

            // Test case where input string contains all required letters plus extra lowercase letters
            Arguments.of("aAbBcC", "ABC", Boolean.TRUE),

            // Test case with only lowercase letters in input
            Arguments.of("abcd", "ABCD", Boolean.TRUE),

            // Test case with an empty second string (b)
            Arguments.of("abc", "", Boolean.TRUE),

            // Test case with an empty first string (a) but non-empty second string (b)
            Arguments.of("", "A", Boolean.FALSE),

            // Complex case with interleaved letters
            Arguments.of("daBcAbCd", "ABCD", Boolean.FALSE));
    }
}
