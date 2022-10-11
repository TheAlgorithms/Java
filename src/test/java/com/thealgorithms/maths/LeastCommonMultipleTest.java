package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LeastCommonMultipleTest {

    /*
     * Test for first number greater than second number
     */
    @Test
    public void testForFirst() {
        int result = LeastCommonMultiple.lcm(6, 8);
        int expected = 24;
        Assertions.assertEquals(result, expected);
    }

    /*
     * Test for second number greater than first number
     */
    @Test
    public void testForSecond() {
        int result = LeastCommonMultiple.lcm(8, 6);
        int expected = 24;
        Assertions.assertEquals(result, expected);
    }
}
