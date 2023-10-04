package com.thealgorithms.bitmanipulation;

/**
 * test Cases of Numbers Different Signs
 * @author Bama Charan Chhandogi
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NumbersDifferentSignsTest {

    @Test
    void testDifferentSignsPositiveNegative() {
        assertTrue(NumbersDifferentSigns.differentSigns(2, -1));
    }

    @Test
    void testDifferentSignsNegativePositive() {
        assertTrue(NumbersDifferentSigns.differentSigns(-3, 7));
    }

    @Test
    void testSameSignsPositive() {
        assertFalse(NumbersDifferentSigns.differentSigns(10, 20));
    }

    @Test
    void testSameSignsNegative() {
        assertFalse(NumbersDifferentSigns.differentSigns(-5, -8));
    }
}
