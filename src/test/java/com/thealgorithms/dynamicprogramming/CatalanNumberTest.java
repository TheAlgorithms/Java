package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CatalanNumberTest {

    @Test
    public void testCatalanNumber() {
        assertEquals(1, CatalanNumber.findNthCatalan(0));
        assertEquals(1, CatalanNumber.findNthCatalan(1));
        assertEquals(42, CatalanNumber.findNthCatalan(5));
        assertEquals(16796, CatalanNumber.findNthCatalan(10));
    }

    @Test
    public void testNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> CatalanNumber.findNthCatalan(-1));
    }
}
