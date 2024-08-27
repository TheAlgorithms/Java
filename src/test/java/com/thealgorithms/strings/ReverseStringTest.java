package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
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
}
