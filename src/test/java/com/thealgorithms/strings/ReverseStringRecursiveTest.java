package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ReverseStringRecursiveTest {
    @ParameterizedTest
    @CsvSource({"'Hello World', 'dlroW olleH'", "'helloworld', 'dlrowolleh'", "'123456789', '987654321'", "'', ''", "'A', 'A'", "'!123 ABC xyz!', '!zyx CBA 321!'", "'Abc 123 Xyz', 'zyX 321 cbA'", "'12.34,56;78:90', '09:87;65,43.21'", "'abcdEFGHiJKL', 'LKJiHGFEdcba'",
        "'MixOf123AndText!', '!txeTdnA321fOxiM'"})
    public void
    testReverseString(String input, String expectedOutput) {
        assertEquals(expectedOutput, ReverseStringRecursive.reverse(input));
    }
}
