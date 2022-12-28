package com.thealgorithms.maths;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PerfectNumberTest {

    @Test
    public void perfectNumber() {
        int trueTestCases[] = { 6, 28, 496, 8128, 33550336 };
        int falseTestCases[] = { -6, 0, 1, 9, 123 };
        for (Integer n : trueTestCases) {
            assertTrue(PerfectNumber.isPerfectNumber(n));
            assertTrue(PerfectNumber.isPerfectNumber2(n));
        }
        for (Integer n : falseTestCases) {
            assertFalse(PerfectNumber.isPerfectNumber(n));
            assertFalse(PerfectNumber.isPerfectNumber2(n));
        }
    }
}
