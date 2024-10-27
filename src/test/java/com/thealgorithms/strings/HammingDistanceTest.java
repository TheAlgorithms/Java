import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * HammingDistanceTest performs unit tests on the HammingDistance class.
 */
class HammingDistanceTest {

    // Valid Input Tests
    @Nested
    @DisplayName("Valid Input Tests")
    class ValidInputTests {

        @ParameterizedTest(name = "Hamming distance between \"{0}\" and \"{1}\" should be {2}")
        @CsvSource({
            "'', '', 0",
            "'java', 'java', 0",
            "'karolin', 'kathrin', 3",
            "'kathrin', 'kerstin', 4",
            "'00000', '11111', 5",
            "'10101', '10100', 1"
        })
        void testHammingDistance(String s1, String s2, int expected) {
            // Assert that the calculated distance matches the expected result
            assertEquals(
                expected,
                HammingDistance.calculateHammingDistance(s1, s2),
                String.format("Expected Hamming distance between '%s' and '%s' is %d", s1, s2, expected)
            );
        }
    }

    // Invalid Input Tests
    @Nested
    @DisplayName("Invalid Input Tests")
    class InvalidInputTests {

        @ParameterizedTest(name = "Expect exception for null inputs: \"{0}\", \"{1}\"")
        @MethodSource("provideNullInputs")
        void testHammingDistanceWithNullInputs(String input1, String input2) {
            // Check for IllegalArgumentException when inputs are null
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> HammingDistance.calculateHammingDistance(input1, input2),
                "Expected IllegalArgumentException for null inputs"
            );
            assertEquals("Input strings cannot be null", exception.getMessage());
        }

        // Provide null input test cases
        private static Stream<Arguments> provideNullInputs() {
            return Stream.of(
                Arguments.of(null, "abc"),
                Arguments.of("abc", null),
                Arguments.of(null, null)
            );
        }

        @Test
        @DisplayName("Should throw exception for unequal string lengths")
        void testNotEqualStringLengths() {
            // Check for IllegalArgumentException when string lengths are unequal
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> HammingDistance.calculateHammingDistance("ab", "abc"),
                "Expected IllegalArgumentException for unequal length strings"
            );
            assertEquals("String lengths must be equal", exception.getMessage());
        }
    }
}
