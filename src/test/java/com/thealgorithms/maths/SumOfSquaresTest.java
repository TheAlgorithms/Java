package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for SumOfSquares
 *
 * @author BEASTSHRIRAM
 */
class SumOfSquaresTest {

    @Test
    void testPerfectSquares() {
        // Perfect squares should return 1
        assertEquals(1, SumOfSquares.minSquares(1)); // 1^2
        assertEquals(1, SumOfSquares.minSquares(4)); // 2^2
        assertEquals(1, SumOfSquares.minSquares(9)); // 3^2
        assertEquals(1, SumOfSquares.minSquares(16)); // 4^2
        assertEquals(1, SumOfSquares.minSquares(25)); // 5^2
    }

    @Test
    void testTwoSquares() {
        // Numbers that can be expressed as sum of two squares
        assertEquals(2, SumOfSquares.minSquares(2)); // 1^2 + 1^2
        assertEquals(2, SumOfSquares.minSquares(5)); // 1^2 + 2^2
        assertEquals(2, SumOfSquares.minSquares(8)); // 2^2 + 2^2
        assertEquals(2, SumOfSquares.minSquares(10)); // 1^2 + 3^2
        assertEquals(2, SumOfSquares.minSquares(13)); // 2^2 + 3^2
    }

    @Test
    void testThreeSquares() {
        // Numbers that require exactly three squares
        assertEquals(3, SumOfSquares.minSquares(3)); // 1^2 + 1^2 + 1^2
        assertEquals(3, SumOfSquares.minSquares(6)); // 1^2 + 1^2 + 2^2
        assertEquals(3, SumOfSquares.minSquares(11)); // 1^2 + 1^2 + 3^2
        assertEquals(3, SumOfSquares.minSquares(12)); // 2^2 + 2^2 + 2^2
        assertEquals(3, SumOfSquares.minSquares(14)); // 1^2 + 2^2 + 3^2
    }

    @Test
    void testFourSquares() {
        // Numbers that require exactly four squares (form 4^a * (8b + 7))
        assertEquals(4, SumOfSquares.minSquares(7)); // 1^2 + 1^2 + 1^2 + 2^2
        assertEquals(4, SumOfSquares.minSquares(15)); // 1^2 + 1^2 + 2^2 + 3^2
        assertEquals(4, SumOfSquares.minSquares(23)); // 1^2 + 1^2 + 3^2 + 3^2
        assertEquals(4, SumOfSquares.minSquares(28)); // 4 * 7, so needs 4 squares
        assertEquals(4, SumOfSquares.minSquares(31)); // 1^2 + 2^2 + 3^2 + 3^2
    }

    @Test
    void testLargerNumbers() {
        // Test some larger numbers
        assertEquals(1, SumOfSquares.minSquares(100)); // 10^2
        assertEquals(2, SumOfSquares.minSquares(65)); // 1^2 + 8^2
        assertEquals(3, SumOfSquares.minSquares(19)); // 1^2 + 3^2 + 3^2
        assertEquals(4, SumOfSquares.minSquares(60)); // 4 * 15, and 15 = 8*1 + 7
    }

    @Test
    void testEdgeCases() {
        // Test edge case
        assertEquals(1, SumOfSquares.minSquares(0)); // 0^2
    }
}
