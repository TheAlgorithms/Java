package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@code Tribonacci}.
 */
public class TribonacciTest {

    /**
     * Tests the Tribonacci computation for a set of known values.
     */
    @Test
    public void testKnownValues() {
        assertEquals(0, Tribonacci.compute(0), "The 0th Tribonacci should be 0.");
        assertEquals(1, Tribonacci.compute(1), "The 1st Tribonacci should be 1.");
        assertEquals(1, Tribonacci.compute(2), "The 2nd Tribonacci should be 1.");
        assertEquals(2, Tribonacci.compute(3), "The 3rd Tribonacci should be 2.");
        assertEquals(4, Tribonacci.compute(4), "The 4th Tribonacci should be 4.");
        assertEquals(7, Tribonacci.compute(5), "The 5th Tribonacci should be 7.");
    }
}
