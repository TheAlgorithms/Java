package com.thealgorithms.bitmanipulation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class IsEvenTest {
    @Test
    void testIsEven() {
        assertTrue(IsEven.isEven(0));
        assertTrue(IsEven.isEven(2));
        assertTrue(IsEven.isEven(-12));
        assertFalse(IsEven.isEven(21));
        assertFalse(IsEven.isEven(-1));
    }
}
