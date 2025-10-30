package com.thealgorithms.dynamicprogramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KadanesAlgorithmTest {
    @Test
    public void testPositiveNumbers() {
        int[] arr = {1, 2, 3, 4};
        Assertions.assertEquals(10, KadanesAlgorithm.maxSubArraySum(arr));
    }

    @Test
    public void testNegativeNumbers() {
        int[] arr = {-1, -2, -3, -4};
        Assertions.assertEquals(-1, KadanesAlgorithm.maxSubArraySum(arr));
    }

    @Test
    public void testMixedNumbers() {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        Assertions.assertEquals(6, KadanesAlgorithm.maxSubArraySum(arr)); // [4, -1, 2, 1]
    }
}
