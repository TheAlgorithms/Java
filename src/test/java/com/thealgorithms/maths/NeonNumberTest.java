package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NeonNumberTest {

    @Test
    public void testIsNeonTrue(){
        assertTrue(NeonNumber.isNeon(9));
        assertTrue(NeonNumber.isNeon(1));
        assertTrue(NeonNumber.isNeon(0));
    }
    @Test
    public void testIsNeonFalse()
    {
        assertFalse(NeonNumber.isNeon(5));
        assertFalse(NeonNumber.isNeon(10));
        assertFalse(NeonNumber.isNeon(25));
    }
}
