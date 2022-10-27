package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CatalanNumberTest {

    @Test
    public void testCatalanNumber() {
        assertEquals(42, CatalanNumber.findNthCatalan(5));
        assertEquals(16796, CatalanNumber.findNthCatalan(10));
    }
}
