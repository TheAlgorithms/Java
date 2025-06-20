package com.thealgorithms.maths;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BinaryPowTest {

    // --- Existing Tests (from your provided code) ---
    @Test
    @DisplayName("Original tests for common cases")
    void testBinPow() {
        assertEquals(4, BinaryPow.binPow(2, 2)); // No 'L' needed for int literals
        assertEquals(256, BinaryPow.binPow(4, 4));
        assertEquals(729, BinaryPow.binPow(9, 3));
        assertEquals(262144, BinaryPow.binPow(8, 6));
    }

    // --- New Comprehensive Tests (integrated and adjusted for binPow(int, int)) ---

    @Test
    @DisplayName("binPow(2, 3) should return 8")
    void testBinPow_basicCase1() {
        assertEquals(8, BinaryPow.binPow(2, 3));
    }

    @Test
    @DisplayName("binPow(5, 2) should return 25")
    void testBinPow_basicCase2() {
        assertEquals(25, BinaryPow.binPow(5, 2));
    }

    @Test
    @DisplayName("binPow(10, 4) should return 10000")
    void testBinPow_basicCase3() {
        assertEquals(10000, BinaryPow.binPow(10, 4));
    }

    // --- Edge Cases and Special Values ---

    @Test
    @DisplayName("binPow(base, 0) should return 1 for non-zero base")
    void testBinPow_exponentZero() {
        assertEquals(1, BinaryPow.binPow(5, 0));
        assertEquals(1, BinaryPow.binPow(1, 0));
        assertEquals(1, BinaryPow.binPow(-10, 0));
        // Removed Long.MAX_VALUE as it exceeds int range
    }

    @Test
    @DisplayName("binPow(0, 0) should return 1 (as per common convention for this algorithm)")
    void testBinPow_zeroToThePowerOfZero() {
        assertEquals(1, BinaryPow.binPow(0, 0));
    }

    @Test
    @DisplayName("binPow(base, 1) should return base")
    void testBinPow_exponentOne() {
        assertEquals(7, BinaryPow.binPow(7, 1));
        assertEquals(-3, BinaryPow.binPow(-3, 1));
        assertEquals(1, BinaryPow.binPow(1, 1));
    }

    @Test
    @DisplayName("binPow(0, positive_exponent) should return 0")
    void testBinPow_zeroBasePositiveExponent() {
        assertEquals(0, BinaryPow.binPow(0, 5));
        assertEquals(0, BinaryPow.binPow(0, 100));
    }

    @Test
    @DisplayName("binPow(1, any_exponent) should return 1")
    void testBinPow_baseOne() {
        assertEquals(1, BinaryPow.binPow(1, 0));
        assertEquals(1, BinaryPow.binPow(1, 5));
        assertEquals(1, BinaryPow.binPow(1, 100));
    }

    @Test
    @DisplayName("binPow(-1, even_exponent) should return 1")
    void testBinPow_negativeOneEvenExponent() {
        assertEquals(1, BinaryPow.binPow(-1, 0));
        assertEquals(1, BinaryPow.binPow(-1, 2));
        assertEquals(1, BinaryPow.binPow(-1, 100));
    }

    @Test
    @DisplayName("binPow(-1, odd_exponent) should return -1")
    void testBinPow_negativeOneOddExponent() {
        assertEquals(-1, BinaryPow.binPow(-1, 1));
        assertEquals(-1, BinaryPow.binPow(-1, 3));
        assertEquals(-1, BinaryPow.binPow(-1, 99));
    }

    @Test
    @DisplayName("binPow(negative_base, even_exponent) should return positive result")
    void testBinPow_negativeBaseEvenExponent() {
        assertEquals(16, BinaryPow.binPow(-2, 4));
        assertEquals(81, BinaryPow.binPow(-3, 4));
    }

    @Test
    @DisplayName("binPow(negative_base, odd_exponent) should return negative result")
    void testBinPow_negativeBaseOddExponent() {
        assertEquals(-8, BinaryPow.binPow(-2, 3));
        assertEquals(-243, BinaryPow.binPow(-3, 5));
    }

    // --- Exception Handling for Negative Exponent ---

    @Test
    @DisplayName("Should throw IllegalArgumentException for negative exponent")
    void testBinPow_negativeExponentThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> BinaryPow.binPow(2, -1));
        assertThrows(IllegalArgumentException.class, () -> BinaryPow.binPow(5, -10));
        assertThrows(IllegalArgumentException.class, () -> BinaryPow.binPow(0, -5));
        assertThrows(IllegalArgumentException.class, () -> BinaryPow.binPow(1, -2));
    }

    // --- Large Number Tests (within int range, careful with potential overflow) ---

    @Test
    @DisplayName("binPow(2, 30) should return 1073741824 (fits in int)")
    void testBinPow_largeExponentFitsInInt() {
        // 2^30 = 1,073,741,824, which fits within Integer.MAX_VALUE (2,147,483,647)
        assertEquals(1073741824, BinaryPow.binPow(2, 30));
    }

    @Test
    @DisplayName("binPow(7, 10) should return 282475249 (fits in int)")
    void testBinPow_anotherLargeExponentFitsInInt() {
        // 7^10 = 282,475,249, which fits within Integer.MAX_VALUE
        assertEquals(282475249, BinaryPow.binPow(7, 10));
    }
}