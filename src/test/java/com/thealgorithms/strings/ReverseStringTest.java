package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ReverseStringTest {

    @ParameterizedTest
    @CsvSource({"'Hello World', 'dlroW olleH'", "'helloworld', 'dlrowolleh'", "'123456789', '987654321'", "'', ''", "'A', 'A'", "'ab', 'ba'", "'  leading and trailing spaces  ', '  secaps gniliart dna gnidael  '", "'!@#$%^&*()', ')(*&^%$#@!'", "'MixOf123AndText!', '!txeTdnA321fOxiM'"})
    public void testReverseString(String input, String expectedOutput) {
        assertEquals(expectedOutput, ReverseString.reverse(input));
        assertEquals(expectedOutput, ReverseString.reverse2(input));
    }
}
