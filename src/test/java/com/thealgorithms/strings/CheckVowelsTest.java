package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CheckVowelsTest {

    @ParameterizedTest
    @CsvSource({"'foo', true", "'bar', true", "'why', false", "'myths', false", "'', false", "'AEIOU', true", "'bcdfghjklmnpqrstvwxyz', false", "'AeIoU', true"})
    void testHasVowels(String input, boolean expected) {
        assertEquals(CheckVowels.hasVowels(input), expected);
    }
}
