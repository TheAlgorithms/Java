package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

/**
 * Test class for HammingDistance calculator.
 * Tests various scenarios including:
 * - Valid string pairs with different Hamming distances
 * - Edge cases (empty strings, same strings)
 * - Invalid inputs (null values, unequal lengths)
 * - Special characters and Unicode strings
 */
@DisplayName("Hamming Distance Calculator Tests")
class HammingDistanceTest {
    
    private static final String ERROR_MESSAGE_UNEQUAL_LENGTH = "String lengths must be equal";
    private static final String ERROR_MESSAGE_NULL_INPUT = "Input strings cannot be null";

    @Nested
    @DisplayName("Valid Input Tests")
    class ValidInputTests {
        
        @ParameterizedTest(name = "Calculate distance between \"{0}\" and \"{1}\" = {2}")
        @CsvSource({
            // Basic cases
            "'', '', 0",
            "'java', 'java', 0",
            "'karolin', 'kathrin', 3",
            "'kathrin', 'kerstin', 4",
            
            // Binary strings
            "'00000', '11111', 5",
            "'10101', '10100', 1",
            
            // Special characters
            "'hello!', 'hello.', 1",
            "'@#$%^&', '@#$%^*', 1",
            
            // Unicode characters
            "'über④⑤', 'uber45', 2",
            "'münchen', 'munchen', 1"
        })
        void shouldCalculateHammingDistance(String s1, String s2, int expected) {
            assertEquals(expected, HammingDistance.calculateHammingDistance(s1, s2),
                    () -> String.format("Failed to calculate correct Hamming distance for '%s' and '%s'", s1, s2));
        }

        @Test
        @DisplayName("Should handle maximum length strings")
        void shouldHandleMaximumLengthStrings() {
            String str1 = "a".repeat(1000);
            String str2 = "b".repeat(1000);
            assertEquals(1000, HammingDistance.calculateHammingDistance(str1, str2),
                    "Should correctly calculate distance for maximum length strings");
        }
    }

    @Nested
    @DisplayName("Invalid Input Tests")
    class InvalidInputTests {
        
        @ParameterizedTest(name = "Test null input: first={0}, second={1}")
        @MethodSource("com.thealgorithms.strings.HammingDistanceTest#provideNullInputs")
        void shouldThrowExceptionForNullInputs(String input1, String input2) {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> HammingDistance.calculateHammingDistance(input1, input2),
                "Should throw IllegalArgumentException for null inputs"
            );
            assertEquals(ERROR_MESSAGE_NULL_INPUT, exception.getMessage(),
                    "Exception message should match for null inputs");
        }

        @ParameterizedTest(name = "Test unequal lengths: \"{0}\" and \"{1}\"")
        @CsvSource({
            "ab, abc",
            "a, aa",
            "hello, hi",
            "'', a"
        })
        void shouldThrowExceptionForUnequalLengths(String s1, String s2) {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> HammingDistance.calculateHammingDistance(s1, s2),
                "Should throw IllegalArgumentException for unequal length strings"
            );
            assertEquals(ERROR_MESSAGE_UNEQUAL_LENGTH, exception.getMessage(),
                    "Exception message should match for unequal lengths");
        }
    }

    // Test data providers
    private static Stream<Arguments> provideNullInputs() {
        return Stream.of(
            Arguments.of(null, "abc"),
            Arguments.of("abc", null),
            Arguments.of(null, null)
        );
    }

    @Test
    @DisplayName("Performance test with large strings")
    void performanceTest() {
        String str1 = "a".repeat(10000) + "b".repeat(10000);
        String str2 = "a".repeat(10000) + "c".repeat(10000);
        
        assertTimeoutPreemptively(
            java.time.Duration.ofSeconds(1),
            () -> HammingDistance.calculateHammingDistance(str1, str2),
            "Should complete calculation within reasonable time"
        );
    }
}
