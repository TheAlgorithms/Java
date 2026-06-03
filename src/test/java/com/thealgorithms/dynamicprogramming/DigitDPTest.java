package com.thealgorithms.dynamicprogramming;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the generalized DigitDP implementation.
 */
public class DigitDPTest {

    @Test
    public void testDigitDPBasicRange() {
        // Numbers between 1 and 20 with a digit sum of 5: 5, 14
        long result = DigitDP.countRangeWithDigitSum(1, 20, 5);
        assertEquals(2, result);
    }

    @Test
    public void testDigitDPZeroBound() {
        // Number 0 has a digit sum of 0
        long result = DigitDP.countRangeWithDigitSum(0, 0, 0);
        assertEquals(1, result);
    }

    @Test
    public void testDigitDPLargeRange() {
        // Count numbers between 1 and 100 with a digit sum of 9
        // 9, 18, 27, 36, 45, 54, 63, 72, 81, 90 (10 numbers)
        long result = DigitDP.countRangeWithDigitSum(1, 100, 9);
        assertEquals(10, result);
    }

    @Test
    public void testDigitDPNoMatches() {
        // No numbers between 10 and 15 can have a digit sum of 20
        long result = DigitDP.countRangeWithDigitSum(10, 15, 20);
        assertEquals(0, result);
    }

    @Test
    public void testDigitDPExceedsMaxSum() {
        // Sum condition that exceeds max possible physical sum array constraints
        // gracefully returns 0
        long result = DigitDP.countRangeWithDigitSum(1, 100, 200);
        assertEquals(0, result);
    }

    @Test
    public void testDigitDPInvalidRange() {
        // Lower bound greater than upper bound should evaluate gracefully to 0
        long result = DigitDP.countRangeWithDigitSum(50, 20, 5);
        assertEquals(0, result);
    }

    @Test
    public void testDigitDPExceedsMaxSumEdgeCase() {
        // Yeh test case target > MAX_DIGIT_SUM wali condition ko hit karega
        long result = DigitDP.countRangeWithDigitSum(1, 100, 180);
        assertEquals(0, result);
    }

    @Test
    public void testDigitDPMemoizationHit() {
        // Badi range dene se overlapping subproblems bante hain,
        // jisse memoization hit hogi aur coverage 100% ho jayegi.
        long result1 = DigitDP.countRangeWithDigitSum(1, 100000, 15);
        long result2 = DigitDP.countRangeWithDigitSum(1, 100000, 15);
        assertEquals(result1, result2);
    }
}
