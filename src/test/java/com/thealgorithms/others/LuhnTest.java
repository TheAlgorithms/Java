package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * White-box tests for {@link Luhn#luhnCheck(int[])}.
 *
 * <p>The method has one loop and two decisions:
 * <ul>
 * <li>D1: {@code i % 2 == 0} - whether the digit at index i is doubled</li>
 * <li>D2: {@code temp > 9} - whether 9 is subtracted from the doubled digit</li>
 * </ul>
 * The tests below cover the loop executed zero, one and many times, both
 * outcomes of each decision, and both outcomes of the final {@code sum % 10 == 0}.
 */
class LuhnTest {

    @Test
    void testEmptyArraySkipsLoopAndIsValid() {
        // Loop body never executes, sum stays 0
        assertTrue(Luhn.luhnCheck(new int[] {}));
    }

    @Test
    void testSingleZeroDigitIsValid() {
        // One iteration: D1 true, D2 false (0 * 2 = 0)
        assertTrue(Luhn.luhnCheck(new int[] {0}));
    }

    @Test
    void testSingleDigitDoubledWithoutSubtractionIsInvalid() {
        // One iteration: D1 true, D2 false (4 * 2 = 8, upper boundary of temp <= 9)
        assertFalse(Luhn.luhnCheck(new int[] {4}));
    }

    @Test
    void testSingleDigitDoubledWithSubtractionIsInvalid() {
        // One iteration: D1 true, D2 true (5 * 2 = 10, lower boundary of temp > 9 -> 1)
        assertFalse(Luhn.luhnCheck(new int[] {5}));
    }

    @Test
    void testTwoDigitsWithoutSubtraction() {
        // i = 1: D1 false -> 2; i = 0: D1 true, D2 false -> 8; sum = 10
        assertTrue(Luhn.luhnCheck(new int[] {4, 2}));
    }

    @Test
    void testTwoDigitsWithSubtraction() {
        // i = 1: D1 false -> 9; i = 0: D1 true, D2 true -> 10 - 9 = 1; sum = 10
        assertTrue(Luhn.luhnCheck(new int[] {5, 9}));
    }

    @Test
    void testTwoDigitsInvalidChecksum() {
        // i = 1: D1 false -> 3; i = 0: D1 true, D2 false -> 8; sum = 11
        assertFalse(Luhn.luhnCheck(new int[] {4, 3}));
    }

    @Test
    void testMaxDigitDoubled() {
        // i = 1: D1 false -> 0; i = 0: D1 true, D2 true -> 18 - 9 = 9; sum = 9
        assertFalse(Luhn.luhnCheck(new int[] {9, 0}));
        // i = 1: D1 false -> 1; i = 0: D1 true, D2 true -> 9; sum = 10
        assertTrue(Luhn.luhnCheck(new int[] {9, 1}));
    }

    @Test
    void testValidCardNumbers() {
        // Many iterations, all branch combinations exercised
        assertTrue(Luhn.luhnCheck(new int[] {4, 5, 6, 1, 2, 6, 1, 2, 1, 2, 3, 4, 5, 4, 6, 7}));
        assertTrue(Luhn.luhnCheck(new int[] {5, 2, 6, 5, 9, 2, 5, 1, 6, 1, 5, 1, 1, 4, 1, 2}));
        assertTrue(Luhn.luhnCheck(new int[] {4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}));
    }

    @Test
    void testCardNumberWithTypoInLastDigitIsInvalid() {
        assertFalse(Luhn.luhnCheck(new int[] {4, 5, 6, 1, 2, 6, 1, 2, 1, 2, 3, 4, 5, 4, 6, 4}));
    }

    @Test
    void testCardNumberWithSingleDigitErrorIsInvalid() {
        // Error in a doubled position (index 0)
        assertFalse(Luhn.luhnCheck(new int[] {3, 5, 6, 1, 2, 6, 1, 2, 1, 2, 3, 4, 5, 4, 6, 7}));
        // Error in a non-doubled position (index 1)
        assertFalse(Luhn.luhnCheck(new int[] {4, 6, 6, 1, 2, 6, 1, 2, 1, 2, 3, 4, 5, 4, 6, 7}));
    }

    @Test
    void testAllZerosIsValid() {
        assertTrue(Luhn.luhnCheck(new int[16]));
    }

    @Test
    void testInputArrayIsNotModified() {
        int[] digits = {5, 2, 6, 5, 9, 2, 5, 1, 6, 1, 5, 1, 1, 4, 1, 2};
        int[] copy = digits.clone();
        Luhn.luhnCheck(digits);
        assertArrayEquals(copy, digits);
    }
}
