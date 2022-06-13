package com.thealgorithms.maths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FindMaxTest {
    public FindMaxTest() {
    }

    @Test
    public void testFindMaxValue() {
        Assertions.assertEquals(10, FindMax.findMax(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }
}
