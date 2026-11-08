package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Test class for CatalanNumbers
 */
class CatalanNumbersTest {

    /**
     * Provides test data for the parameterized Catalan number test.
     * Each array contains two elements:
     * [input number, expected Catalan number for that input]
     */
    static Stream<Object[]> catalanNumbersProvider() {
        return Stream.of(new Object[] {0, 1L}, new Object[] {1, 1L}, new Object[] {2, 2L}, new Object[] {3, 5L}, new Object[] {4, 14L}, new Object[] {5, 42L}, new Object[] {6, 132L}, new Object[] {7, 429L}, new Object[] {8, 1430L}, new Object[] {9, 4862L}, new Object[] {10, 16796L},
            new Object[] {11, 58786L}, new Object[] {15, 9694845L}, new Object[] {35, 3116285494907301262L});
    }

    /**
     * Parameterized test for checking the correctness of Catalan numbers.
     * Uses the data from the provider method 'catalanNumbersProvider'.
     */
    @ParameterizedTest
    @MethodSource("catalanNumbersProvider")
    void testCatalanNumbers(int input, long expected) {
        assertEquals(expected, CatalanNumbers.catalan(input), () -> String.format("Catalan number for input %d should be %d", input, expected));
    }

    /**
     * Test for invalid inputs which should throw an IllegalArgumentException.
     */
    @Test
    void testIllegalInput() {
        assertThrows(IllegalArgumentException.class, () -> CatalanNumbers.catalan(-1));
        assertThrows(IllegalArgumentException.class, () -> CatalanNumbers.catalan(-5));
        assertThrows(IllegalArgumentException.class, () -> CatalanNumbers.catalan(36));
    }
}
