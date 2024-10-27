package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Test class for HammingDistance calculator.
 */
class HammingDistanceTest {

    private static final String ERROR_MESSAGE_UNEQUAL_LENGTH = "String lengths must be equal";
    private static final String ERROR_MESSAGE_NULL_INPUT = "Input strings cannot be null";

    @Nested
    class ValidInputTests {

        @ParameterizedTest(name = "Calculate distance between {0} and {1} = {2}")
        @CsvSource({
            ", , 0",
            "java, java, 0",
            "karolin, kathrin, 3",
            "kathrin, kerstin, 4",
            "00000, 11111, 5",
            "10101, 10100, 1",
            "hello!, hello., 1",
            "@#$%^&, @#$%^*, 1"
        })
        void shouldCalculateHammingDistance(String s1, String s2, int expected) {
            assertEquals(
                expected,
                HammingDistance.calculateHammingDistance(s1, s2),
                String.format("Failed to calculate correct Hamming distance for '%s' and '%s'", s1, s2)
            );
        }

        @Test
        void shouldHandleMaximumLengthStrings() {
            String str1 = "a".repeat(1000);
            String str2 = "b".repeat(1000);
            assertEquals(
                1000,
                HammingDistance.calculateHammingDistance(str1, str2),
                "Should correctly calculate distance for maximum length strings"
            );
        }
    }

    @Nested
    class InvalidInputTests {

        @ParameterizedTest(name = "Test null input: first={0}, second={1}")
        @MethodSource("com.thealgorithms.strings.HammingDistanceTest#provideNullInputs")
        void shouldThrowExceptionForNullInputs(String input1, String input2) {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> HammingDistance.calculateHammingDistance(input1, input2),
                "Should throw IllegalArgumentException for null inputs"
            );
            assertEquals(
                ERROR_MESSAGE_NULL_INPUT,
                exception.getMessage(),
                "Exception message should match for null inputs"
            );
        }

        @ParameterizedTest(name = "Test unequal lengths: {0} and {1}")
        @CsvSource({
            "ab, abc",
            "a, aa",
            "hello, hi",
            ", a"
        })
        void shouldThrowExceptionForUnequalLengths(String s1, String s2) {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> HammingDistance.calculateHammingDistance(s1, s2),
                "Should throw IllegalArgumentException for unequal length strings"
            );
            assertEquals(
                ERROR_MESSAGE_UNEQUAL_LENGTH,
                exception.getMessage(),
                "Exception message should match for unequal lengths"
            );
        }
    }

    private static Stream<Arguments> provideNullInputs() {
        return Stream.of(
            Arguments.of(null, "abc"),
            Arguments.of("abc", null),
            Arguments.of(null, null)
        );
    }

    @Test
    void performanceTest() {
        String str1 = "a".repeat(10000) + "b".repeat(10000);
        String str2 = "a".repeat(10000) + "c".repeat(10000);

        assertTimeoutPreemptively(
            Duration.ofSeconds(1),
            () -> HammingDistance.calculateHammingDistance(str1, str2),
            "Should complete calculation within reasonable time"
        );
    }
}
