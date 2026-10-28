package com.thealgorithms.maths;
// author: Vraj Prajapati @Rosander0

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FriendlyNumberTest {

    @Test
    public void testFriendlyNumbers() {
        // 6 and 28 are friendly (abundancy index = 2)
        assertTrue(FriendlyNumber.areFriendly(6, 28));
        // Every number is friendly with itself
        assertTrue(FriendlyNumber.areFriendly(6, 6));
        assertTrue(FriendlyNumber.areFriendly(1, 1));
    }

    @Test
    public void testNonFriendlyNumbers() {
        assertFalse(FriendlyNumber.areFriendly(6, 10));
        assertFalse(FriendlyNumber.areFriendly(10, 15));
        assertFalse(FriendlyNumber.areFriendly(4, 9));
    }

    @Test
    public void testInvalidInputs() {
        assertFalse(FriendlyNumber.areFriendly(0, 6));
        assertFalse(FriendlyNumber.areFriendly(-1, 6));
        assertFalse(FriendlyNumber.areFriendly(6, -1));
    }
}
