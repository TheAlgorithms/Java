package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("Alphabetical.isAlphabetical()")
class AlphabeticalTest {

    static Stream<Arguments> testCases() {
        return Stream.of(arguments("", false, "Should return false for empty string"), arguments("   ", false, "Should return false for blank string"), arguments("a1b2", false, "Should return false when string contains numbers"),
            arguments("abc!DEF", false, "Should return false when string contains symbols"), arguments("#abc", false, "Should return false when first character is not a letter"), arguments("abc", true, "Should return true for non-decreasing order"),
            arguments("aBcD", true, "Should return true for mixed case increasing sequence"), arguments("a", true, "Should return true for single letter"), arguments("'", false, "Should return false for single symbol"), arguments("aabbcc", true, "Should return true for repeated letters"),
            arguments("cba", false, "Should return false when order decreases"), arguments("abzba", false, "Should return false when middle breaks order"));
    }

    private void assertAlphabetical(String input, boolean expected, String message) {
        // Arrange & Act
        boolean result = Alphabetical.isAlphabetical(input);

        // Assertion
        assertEquals(expected, result, message);
    }

    @Test
    @DisplayName("Should return false for null input")
    void nullInputTest() {
        assertAlphabetical(null, false, "Should return false for null input");
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("testCases")
    @DisplayName("Alphabetical cases")
    void isAlphabeticalTest(String input, boolean expected, String message) {
        assertAlphabetical(input, expected, message);
    }
}
