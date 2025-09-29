package com.thealgorithms.strings;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

// Test class for Knuth-Morris-Pratt algorithm
class KnuthMorrisPrattTest {

    // Method to provide test data
    private static Stream<Object[]> provideTestData() {
        return Stream.of(new Object[] {"ababacababaad", "ababa", List.of(0, 6)}, new Object[] {"hello world", "world", List.of(6)}, new Object[] {"aaaaa", "b", List.of()}, new Object[] {"BBCDEFG", "AAB", List.of()}, new Object[] {"ABABDABACD", "ABABC", List.of()});
    }

    // Parameterized test method for the search function
    @ParameterizedTest(name = "{0} and {1} should return {2}")
    @MethodSource("provideTestData")
    void searchTest(String test, String expected, List<Integer> expectedResult) {
        List<Integer> result = KnuthMorrisPratt.search(test, expected);
        assertIterableEquals(result, expectedResult);
    }
}
