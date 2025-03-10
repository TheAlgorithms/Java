package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StringCompressionTest {
    @ParameterizedTest
    @CsvSource({"'a', 'a'", "'aabbb', 'a2b3'", "'abbbc', 'ab3c'", "'aabccd', 'a2bc2d'", "'aaaabbbcccc', 'a4b3c4'", "'abcd', 'abcd'", "'aabbccdd', 'a2b2c2d2'", "'aaabbaa', 'a3b2a2'", "'', ''", "'a', 'a'", "'aaaaa', 'a5'", "'aabb', 'a2b2'", "'aabbbaaa', 'a2b3a3'", "'qwerty', 'qwerty'"})
    void stringCompressionTest(String input, String expectedOutput) {
        String output = StringCompression.compress(input);
        assertEquals(expectedOutput, output);
    }
}
