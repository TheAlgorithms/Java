package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for CountBitsFlip.
 * Covers:
 *  - simple examples
 *  - zeros and identical values
 *  - negative numbers and two's complement edge cases
 *  - Long.MIN_VALUE / Long.MAX_VALUE
 *  - randomized consistency checks between two implementations
 */
@DisplayName("CountBitsFlip Tests")
class CountBitsFlipTest {

    @Test
    @DisplayName("Example: A=10, B=20 => 4 bits")
    void exampleTenTwenty() {
        long a = 10L;
        long b = 20L;
        long expected = 4L;
        assertEquals(expected, CountBitsFlip.countBitsFlip(a, b), "Brian Kernighan implementation should return 4");
        assertEquals(expected, CountBitsFlip.countBitsFlipAlternative(a, b), "Long.bitCount implementation should return 4");
    }

    @Test
    @DisplayName("Identical values => 0 bits")
    void identicalValues() {
        long a = 123456789L;
        long b = 123456789L;
        long expected = 0L;
        assertEquals(expected, CountBitsFlip.countBitsFlip(a, b));
        assertEquals(expected, CountBitsFlip.countBitsFlipAlternative(a, b));
    }

    @Test
    @DisplayName("Both zeros => 0 bits")
    void bothZeros() {
        assertEquals(0L, CountBitsFlip.countBitsFlip(0L, 0L));
        assertEquals(0L, CountBitsFlip.countBitsFlipAlternative(0L, 0L));
    }

    @Test
    @DisplayName("Small example: A=15 (1111), B=8 (1000) => 3 bits")
    void smallExample() {
        long a = 15L; // 1111
        long b = 8L; // 1000
        long expected = 3L; // differs in three low bits
        assertEquals(expected, CountBitsFlip.countBitsFlip(a, b));
        assertEquals(expected, CountBitsFlip.countBitsFlipAlternative(a, b));
    }

    @Test
    @DisplayName("Negative values: -1 vs 0 => 64 bits (two's complement all ones)")
    void negativeVsZero() {
        long a = -1L;
        long b = 0L;
        long expected = 64L; // all 64 bits differ
        assertEquals(expected, CountBitsFlip.countBitsFlip(a, b));
        assertEquals(expected, CountBitsFlip.countBitsFlipAlternative(a, b));
    }

    @Test
    @DisplayName("Long.MIN_VALUE vs Long.MAX_VALUE => 64 bits")
    void minMaxLongs() {
        long a = Long.MIN_VALUE;
        long b = Long.MAX_VALUE;
        long expected = 64L; // MAX ^ MIN yields all ones on 64-bit long
        assertEquals(expected, CountBitsFlip.countBitsFlip(a, b));
        assertEquals(expected, CountBitsFlip.countBitsFlipAlternative(a, b));
    }

    @Test
    @DisplayName("Randomized consistency: both implementations agree across many pairs")
    void randomizedConsistency() {
        final int iterations = 1000;
        final Random rnd = new Random(12345L); // deterministic seed for reproducibility

        for (int i = 0; i < iterations; i++) {
            long a = rnd.nextLong();
            long b = rnd.nextLong();

            long res1 = CountBitsFlip.countBitsFlip(a, b);
            long res2 = CountBitsFlip.countBitsFlipAlternative(a, b);

            assertEquals(res2, res1, () -> String.format("Mismatch for a=%d, b=%d: impl1=%d, impl2=%d", a, b, res1, res2));
        }
    }
}
