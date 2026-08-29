package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DisariumNumberTest {

    @Test
    void testDisariumNumbers() {
        assertTrue(DisariumNumber.isDisarium(1));
        assertTrue(DisariumNumber.isDisarium(89));
        assertTrue(DisariumNumber.isDisarium(135));
        assertTrue(DisariumNumber.isDisarium(175));
        assertTrue(DisariumNumber.isDisarium(518));
    }

    @Test
    void testNonDisariumNumbers() {
        assertFalse(DisariumNumber.isDisarium(10));
        assertFalse(DisariumNumber.isDisarium(100));
        assertFalse(DisariumNumber.isDisarium(200));
    }

    @Test
    void testInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> DisariumNumber.isDisarium(0));
        assertThrows(IllegalArgumentException.class, () -> DisariumNumber.isDisarium(-5));
    }
}
