package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class HappyNumberTest {

    @Test
    void testHappyNumbers() {
        // Known happy numbers
        assertTrue(HappyNumber.isHappy(1));
        assertTrue(HappyNumber.isHappy(7));
        assertTrue(HappyNumber.isHappy(19));
        assertTrue(HappyNumber.isHappy(100));
    }

    @Test
    void testUnhappyNumbers() {
        // Known unhappy numbers
        assertFalse(HappyNumber.isHappy(2));
        assertFalse(HappyNumber.isHappy(4));
        assertFalse(HappyNumber.isHappy(20));
    }

    @Test
    void testLargeNumber() {
        // Just to check behavior with larger input
        assertTrue(HappyNumber.isHappy(1000000)); // reduces to 1 eventually
    }
}
