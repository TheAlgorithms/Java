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
        // Workaround for SpotBugs false positive (NAB_NEEDLESS_BOOLEAN_CONSTANT_CONVERSION)
        // due to JUnit Arguments.of(Object...) auto-boxing
        return Stream.of(arguments("", Boolean.FALSE, "Should return false for empty string"), arguments("   ", Boolean.FALSE, "Should return false for blank string"), arguments("a1b2", Boolean.FALSE, "Should return false when string contains numbers"),
            arguments("abc!DEF", Boolean.FALSE, "Should return false when string contains symbols"), arguments("#abc", Boolean.FALSE, "Should return false when first character is not a letter"), arguments("abc", Boolean.TRUE, "Should return true for non-decreasing order"),
            arguments("aBcD", Boolean.TRUE, "Should return true for mixed case increasing sequence"), arguments("a", Boolean.TRUE, "Should return true for single letter"), arguments("'", Boolean.FALSE, "Should return false for single symbol"),
            arguments("aabbcc", Boolean.TRUE, "Should return true for repeated letters"), arguments("cba", Boolean.FALSE, "Should return false when order decreases"), arguments("abzba", Boolean.FALSE, "Should return false when middle breaks order"));
    }

    private void assertAlphabetical(String input, boolean expected, String message) {
        // Arrange & Act
        boolean result = Alphabetical.isAlphabetical(input);

        // Assert
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
