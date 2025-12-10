package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AlphabeticalTest {

    @ParameterizedTest(name = "\"{0}\" → Expected: {1}")
    @CsvSource({"'abcdefghijklmno', true", "'abcdxxxyzzzz', true", "'123a', false", "'abcABC', false", "'abcdefghikjlmno', false", "'aBC', true", "'abc', true", "'xyzabc', false", "'abcxyz', true", "'', false", "'1', false"})
    void testIsAlphabetical(String input, boolean expected) {
        assertEquals(expected, Alphabetical.isAlphabetical(input));
    }
}
