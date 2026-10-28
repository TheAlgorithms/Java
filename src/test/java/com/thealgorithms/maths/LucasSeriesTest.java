package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Test cases for {@link LucasSeries} class.
 * Tests both recursive and iterative implementations for correctness,
 * edge cases, and error handling.
 */
class LucasSeriesTest {

    /**
     * Test the first Lucas number L(1) = 2
     */
    @Test
    void testFirstLucasNumber() {
        assertEquals(2, LucasSeries.lucasSeries(1));
        assertEquals(2, LucasSeries.lucasSeriesIteration(1));
    }

    /**
     * Test the second Lucas number L(2) = 1
     */
    @Test
    void testSecondLucasNumber() {
        assertEquals(1, LucasSeries.lucasSeries(2));
        assertEquals(1, LucasSeries.lucasSeriesIteration(2));
    }

    /**
     * Test the third Lucas number L(3) = 3
     */
    @Test
    void testThirdLucasNumber() {
        assertEquals(3, LucasSeries.lucasSeries(3));
        assertEquals(3, LucasSeries.lucasSeriesIteration(3));
    }

    /**
     * Test the fourth Lucas number L(4) = 4
     */
    @Test
    void testFourthLucasNumber() {
        assertEquals(4, LucasSeries.lucasSeries(4));
        assertEquals(4, LucasSeries.lucasSeriesIteration(4));
    }

    /**
     * Test the fifth Lucas number L(5) = 7
     */
    @Test
    void testFifthLucasNumber() {
        assertEquals(7, LucasSeries.lucasSeries(5));
        assertEquals(7, LucasSeries.lucasSeriesIteration(5));
    }

    /**
     * Test the sixth Lucas number L(6) = 11
     */
    @Test
    void testSixthLucasNumber() {
        assertEquals(11, LucasSeries.lucasSeries(6));
        assertEquals(11, LucasSeries.lucasSeriesIteration(6));
    }

    /**
     * Test the seventh Lucas number L(7) = 18
     */
    @Test
    void testSeventhLucasNumber() {
        assertEquals(18, LucasSeries.lucasSeries(7));
        assertEquals(18, LucasSeries.lucasSeriesIteration(7));
    }

    /**
     * Test the eighth Lucas number L(8) = 29
     */
    @Test
    void testEighthLucasNumber() {
        assertEquals(29, LucasSeries.lucasSeries(8));
        assertEquals(29, LucasSeries.lucasSeriesIteration(8));
    }

    /**
     * Test the ninth Lucas number L(9) = 47
     */
    @Test
    void testNinthLucasNumber() {
        assertEquals(47, LucasSeries.lucasSeries(9));
        assertEquals(47, LucasSeries.lucasSeriesIteration(9));
    }

    /**
     * Test the tenth Lucas number L(10) = 76
     */
    @Test
    void testTenthLucasNumber() {
        assertEquals(76, LucasSeries.lucasSeries(10));
        assertEquals(76, LucasSeries.lucasSeriesIteration(10));
    }

    /**
     * Test the eleventh Lucas number L(11) = 123
     */
    @Test
    void testEleventhLucasNumber() {
        assertEquals(123, LucasSeries.lucasSeries(11));
        assertEquals(123, LucasSeries.lucasSeriesIteration(11));
    }

    /**
     * Test larger Lucas numbers to ensure correctness
     */
    @Test
    void testLargerLucasNumbers() {
        assertEquals(199, LucasSeries.lucasSeries(12));
        assertEquals(199, LucasSeries.lucasSeriesIteration(12));
        assertEquals(322, LucasSeries.lucasSeries(13));
        assertEquals(322, LucasSeries.lucasSeriesIteration(13));
        assertEquals(521, LucasSeries.lucasSeries(14));
        assertEquals(521, LucasSeries.lucasSeriesIteration(14));
        assertEquals(843, LucasSeries.lucasSeries(15));
        assertEquals(843, LucasSeries.lucasSeriesIteration(15));
    }

    /**
     * Test that both methods produce the same results
     */
    @Test
    void testRecursiveAndIterativeConsistency() {
        for (int i = 1; i <= 15; i++) {
            assertEquals(LucasSeries.lucasSeries(i), LucasSeries.lucasSeriesIteration(i), "Mismatch at position " + i);
        }
    }

    /**
     * Test invalid input: zero
     */
    @Test
    void testZeroInputThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> LucasSeries.lucasSeries(0));
        assertThrows(IllegalArgumentException.class, () -> LucasSeries.lucasSeriesIteration(0));
    }

    /**
     * Test invalid input: negative number
     */
    @Test
    void testNegativeInputThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> LucasSeries.lucasSeries(-1));
        assertThrows(IllegalArgumentException.class, () -> LucasSeries.lucasSeriesIteration(-1));
        assertThrows(IllegalArgumentException.class, () -> LucasSeries.lucasSeries(-5));
        assertThrows(IllegalArgumentException.class, () -> LucasSeries.lucasSeriesIteration(-5));
    }
}
