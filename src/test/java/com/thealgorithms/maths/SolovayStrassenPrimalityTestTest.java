package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit tests for the {@link SolovayStrassenPrimalityTest} class.
 * This class tests the functionality of the Solovay-Strassen primality test implementation.
 */
class SolovayStrassenPrimalityTestTest {

    private static final int RANDOM_SEED = 123; // Seed for reproducibility
    private SolovayStrassenPrimalityTest testInstance;

    /**
     * Sets up a new instance of {@link SolovayStrassenPrimalityTest}
     * before each test case, using a fixed random seed for consistency.
     */
    @BeforeEach
    void setUp() {
        testInstance = SolovayStrassenPrimalityTest.getSolovayStrassenPrimalityTest(RANDOM_SEED);
    }

    /**
     * Provides test cases for prime numbers with various values of n and k (iterations).
     *
     * @return an array of objects containing pairs of n and k values
     */
    static Object[][] primeNumbers() {
        return new Object[][] {{2, 1}, {3, 1}, {5, 5}, {7, 10}, {11, 20}, {13, 10}, {17, 5}, {19, 1}};
    }

    /**
     * Tests known prime numbers with various values of n and k (iterations).
     *
     * @param n the number to be tested for primality
     * @param k the number of iterations to use in the primality test
     */
    @ParameterizedTest
    @MethodSource("primeNumbers")
    void testPrimeNumbersWithDifferentNAndK(int n, int k) {
        assertTrue(testInstance.solovayStrassen(n, k), n + " should be prime");
    }

    /**
     * Provides test cases for composite numbers with various values of n and k (iterations).
     *
     * @return an array of objects containing pairs of n and k values
     */
    static Object[][] compositeNumbers() {
        return new Object[][] {{4, 1}, {6, 5}, {8, 10}, {9, 20}, {10, 1}, {12, 5}, {15, 10}};
    }

    /**
     * Tests known composite numbers with various values of n and k (iterations).
     *
     * @param n the number to be tested for primality
     * @param k the number of iterations to use in the primality test
     */
    @ParameterizedTest
    @MethodSource("compositeNumbers")
    void testCompositeNumbersWithDifferentNAndK(int n, int k) {
        assertFalse(testInstance.solovayStrassen(n, k), n + " should be composite");
    }

    /**
     * Tests edge cases for the primality test.
     * This includes negative numbers and small integers (0 and 1).
     */
    @Test
    void testEdgeCases() {
        assertFalse(testInstance.solovayStrassen(-1, 10), "-1 should not be prime");
        assertFalse(testInstance.solovayStrassen(0, 10), "0 should not be prime");
        assertFalse(testInstance.solovayStrassen(1, 10), "1 should not be prime");

        // Test small primes and composites
        assertTrue(testInstance.solovayStrassen(2, 1), "2 is a prime number (single iteration)");
        assertFalse(testInstance.solovayStrassen(9, 1), "9 is a composite number (single iteration)");

        // Test larger primes and composites
        long largePrime = 104729; // Known large prime number
        long largeComposite = 104730; // Composite number (even)

        assertTrue(testInstance.solovayStrassen(largePrime, 20), "104729 is a prime number");
        assertFalse(testInstance.solovayStrassen(largeComposite, 20), "104730 is a composite number");

        // Test very large numbers (may take longer)
        long veryLargePrime = 512927357; // Known very large prime number
        long veryLargeComposite = 512927358; // Composite number (even)

        assertTrue(testInstance.solovayStrassen(veryLargePrime, 20), Long.MAX_VALUE - 1 + " is likely a prime number.");

        assertFalse(testInstance.solovayStrassen(veryLargeComposite, 20), Long.MAX_VALUE + " is a composite number.");
    }

    /**
     * Tests the Jacobi symbol calculation directly for known values.
     * This verifies that the Jacobi symbol method behaves as expected.
     */
    @Test
    void testJacobiSymbolCalculation() {
        // Jacobi symbol (a/n) where n is odd and positive
        int jacobi1 = testInstance.calculateJacobi(6, 11); // Should return -1
        int jacobi2 = testInstance.calculateJacobi(5, 11); // Should return +1

        assertEquals(-1, jacobi1);
        assertEquals(+1, jacobi2);

        // Edge case: Jacobi symbol with even n or non-positive n
        int jacobi4 = testInstance.calculateJacobi(5, -11); // Should return 0 (invalid)
        int jacobi5 = testInstance.calculateJacobi(5, 0); // Should return 0 (invalid)

        assertEquals(0, jacobi4);
        assertEquals(0, jacobi5);
    }
}
