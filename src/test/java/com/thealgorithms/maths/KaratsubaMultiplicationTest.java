package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit test class for {@link KaratsubaMultiplication} class.
 *
 * <p>
 * This class tests various edge cases and normal cases for the
 * Karatsuba multiplication algorithm implemented in the KaratsubaMultiplication class.
 * It uses parameterized tests to handle multiple test cases.
 * </p>
 */
class KaratsubaMultiplicationTest {

    /**
     * Provides test data for the parameterized test.
     * Each entry in the stream contains three elements: x, y, and the expected result.
     *
     * @return a stream of arguments for the parameterized test
     */
    static Stream<Arguments> provideTestCases() {
        return Stream.of(
            // Test case 1: Two small numbers
            Arguments.of(new BigInteger("1234"), new BigInteger("5678"), new BigInteger("7006652")),
            // Test case 2: Two large numbers
            Arguments.of(new BigInteger("342364"), new BigInteger("393958"), new BigInteger("134877036712")),
            // Test case 3: One number is zero
            Arguments.of(BigInteger.ZERO, new BigInteger("5678"), BigInteger.ZERO),
            // Test case 4: Both numbers are zero
            Arguments.of(BigInteger.ZERO, BigInteger.ZERO, BigInteger.ZERO),
            // Test case 5: Single-digit numbers
            Arguments.of(new BigInteger("9"), new BigInteger("8"), new BigInteger("72")));
    }

    /**
     * Parameterized test for Karatsuba multiplication.
     *
     * <p>
     * This method runs the Karatsuba multiplication algorithm for multiple test cases.
     * </p>
     *
     * @param x the first number to multiply
     * @param y the second number to multiply
     * @param expected the expected result of x * y
     */
    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testKaratsubaMultiplication(BigInteger x, BigInteger y, BigInteger expected) {
        assertEquals(expected, KaratsubaMultiplication.karatsuba(x, y));
    }
}
