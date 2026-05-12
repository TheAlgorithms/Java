package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class NeonNumberTest {

    @Test
    public void testIsNeonTrue() {
        assertTrue(NeonNumber.isNeon(0));
        assertTrue(NeonNumber.isNeon(1));
        assertTrue(NeonNumber.isNeon(9));
    }

    @Test
    public void testIsNeonFalse() {
        assertFalse(NeonNumber.isNeon(2));
        assertFalse(NeonNumber.isNeon(5));
        assertFalse(NeonNumber.isNeon(10));
    }
}
