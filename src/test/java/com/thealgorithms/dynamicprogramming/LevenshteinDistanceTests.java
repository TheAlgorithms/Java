package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LevenshteinDistanceTests {

    @ParameterizedTest
    @CsvSource({// String 1, String 2, Expected Distance
        "'', '', 0", "'Hello, World!', 'Hello, World!', 0", "'', 'Rust', 4", "horse, ros, 3", "tan, elephant, 6", "execute, intention, 8"})
    void
    naiveLevenshteinDistanceTest(String str1, String str2, int expectedDistance) {
        int result = LevenshteinDistance.naiveLevenshteinDistance(str1, str2);
        assertEquals(expectedDistance, result);
    }

    @ParameterizedTest
    @CsvSource({// String 1, String 2, Expected Distance
        "'', '', 0", "'Hello, World!', 'Hello, World!', 0", "'', 'Rust', 4", "horse, ros, 3", "tan, elephant, 6", "execute, intention, 8"})
    void
    optimizedLevenshteinDistanceTest(String str1, String str2, int expectedDistance) {
        int result = LevenshteinDistance.optimizedLevenshteinDistance(str1, str2);
        assertEquals(expectedDistance, result);
    }
}
