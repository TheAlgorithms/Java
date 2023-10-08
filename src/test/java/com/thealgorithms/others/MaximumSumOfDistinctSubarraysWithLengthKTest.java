package com.thealgorithms.others;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MaximumSumOfDistinctSubarraysWithLengthKTest {
    @Test
    public void SampleTestCase1() {
        assertEquals(15, MaximumSumOfDistinctSubarraysWithLengthK.maximumSubarraySum(3, 1, 5, 4, 2, 9, 9, 9));
    }

    @Test
    public void SampleTestCase2() {
        assertEquals(0, MaximumSumOfDistinctSubarraysWithLengthK.maximumSubarraySum(3, 4, 4, 4));
    }

    @Test
    public void SampleTestCase3() {
        assertEquals(12, MaximumSumOfDistinctSubarraysWithLengthK.maximumSubarraySum(3, 9, 9, 9, 1, 2, 3));
    }

    @Test
    public void EdgeCase1() {
        assertEquals(0, MaximumSumOfDistinctSubarraysWithLengthK.maximumSubarraySum(0, 9, 9, 9));
    }

    @Test
    public void EdgeCase2() {
        assertEquals(0, MaximumSumOfDistinctSubarraysWithLengthK.maximumSubarraySum(5, 9, 9, 9));
    }
}
