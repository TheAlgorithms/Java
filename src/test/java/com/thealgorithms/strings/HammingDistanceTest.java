package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class HammingDistanceTest {

    @ParameterizedTest
    @CsvSource({"'', '', 0", "'java', 'java', 0", "'karolin', 'kathrin', 3", "'kathrin', 'kerstin', 4", "'00000', '11111', 5", "'10101', '10100', 1"})
    void testHammingDistance(String s1, String s2, int expected) {
        assertEquals(expected, HammingDistance.calculateHammingDistance(s1, s2));
    }

    @ParameterizedTest
    @MethodSource("provideNullInputs")
    void testHammingDistanceWithNullInputs(String input1, String input2) {
        assertThrows(IllegalArgumentException.class, () -> HammingDistance.calculateHammingDistance(input1, input2));
    }

    private static Stream<Arguments> provideNullInputs() {
        return Stream.of(Arguments.of(null, "abc"), Arguments.of("abc", null), Arguments.of(null, null));
    }

    @Test
    void testNotEqualStringLengths() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> HammingDistance.calculateHammingDistance("ab", "abc"));
        assertEquals("String lengths must be equal", exception.getMessage());
    }
}
