package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class ReverseStringTest {

    private static Stream<Arguments> testCases() {
        return Stream.of(Arguments.of("Hello World", "dlroW olleH"), Arguments.of("helloworld", "dlrowolleh"), Arguments.of("123456789", "987654321"), Arguments.of("", ""), Arguments.of("A", "A"), Arguments.of("ab", "ba"),
            Arguments.of("  leading and trailing spaces  ", "  secaps gniliart dna gnidael  "), Arguments.of("!@#$%^&*()", ")(*&^%$#@!"), Arguments.of("MixOf123AndText!", "!txeTdnA321fOxiM"));
    }

    @ParameterizedTest
    @MethodSource("testCases")
    public void testReverseString(String input, String expectedOutput) {
        assertEquals(expectedOutput, ReverseString.reverse(input));
    }

    @ParameterizedTest
    @MethodSource("testCases")
    public void testReverseString2(String input, String expectedOutput) {
        assertEquals(expectedOutput, ReverseString.reverse2(input));
    }

    @ParameterizedTest
    @MethodSource("testCases")
    public void testReverseString3(String input, String expectedOutput) {
        assertEquals(expectedOutput, ReverseString.reverse3(input));
    }

    @ParameterizedTest
    @MethodSource("testCases")
    public void testReverseStringUsingStack(String input, String expectedOutput) {
        assertEquals(expectedOutput, ReverseString.reverseStringUsingStack(input));
    }

    @Test
    public void testReverseStringUsingStackWithNullInput() {
        assertThrows(IllegalArgumentException.class, () -> ReverseString.reverseStringUsingStack(null));
    }

    @ParameterizedTest
    @CsvSource({"'Hello World', 'dlroW olleH'", "'helloworld', 'dlrowolleh'", "'123456789', '987654321'", "'', ''", "'A', 'A'", "'!123 ABC xyz!', '!zyx CBA 321!'", "'Abc 123 Xyz', 'zyX 321 cbA'", "'12.34,56;78:90', '09:87;65,43.21'", "'abcdEFGHiJKL', 'LKJiHGFEdcba'",
        "'MixOf123AndText!', '!txeTdnA321fOxiM'"})
    public void
    testReverseStringUsingRecursion(String input, String expectedOutput) {
        assertEquals(expectedOutput, ReverseString.reverseStringUsingRecursion(input));
    }
}
