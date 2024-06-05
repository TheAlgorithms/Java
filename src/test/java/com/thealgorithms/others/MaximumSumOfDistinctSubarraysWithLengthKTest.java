package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaximumSumOfDistinctSubarraysWithLengthKTest {
    @Test
    public void sampleTestCase1() {
        assertEquals(15, MaximumSumOfDistinctSubarraysWithLengthK.maximumSubarraySum(3, 1, 5, 4, 2, 9, 9, 9));
    }

    @Test
    public void sampleTestCase2() {
        assertEquals(0, MaximumSumOfDistinctSubarraysWithLengthK.maximumSubarraySum(3, 4, 4, 4));
    }

    @Test
    public void sampleTestCase3() {
        assertEquals(12, MaximumSumOfDistinctSubarraysWithLengthK.maximumSubarraySum(3, 9, 9, 9, 1, 2, 3));
    }

    @Test
    public void edgeCase1() {
        assertEquals(0, MaximumSumOfDistinctSubarraysWithLengthK.maximumSubarraySum(0, 9, 9, 9));
    }

    @Test
    public void edgeCase2() {
        assertEquals(0, MaximumSumOfDistinctSubarraysWithLengthK.maximumSubarraySum(5, 9, 9, 9));
    }
}
