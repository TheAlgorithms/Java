package com.DynamicProgramming;

import org.testng.annotations.Test;

import static com.DynamicProgramming.KadaneAlgorithm.*;
import static org.junit.jupiter.api.Assertions.*;
/*
*
* Test Junit with All-DU path
*
* Detail (chi tiết cách làm):
*   https://docs.google.com/document/d/18XZbdkmoXaNTjO3WfDCpQsCaxDgLfSPtZtKWe-7UpNA/edit?usp=sharing
*
* */

public class KadaneAlgorithmTest {
    @Test
    void KadaneAlgorithmTest1() {
        int arr[] = {};
        assertEquals(0, largestContiguousSum(arr));
    }
    @Test
    void KadaneAlgorithmTest2() {
        int arr[] = {1, 0};
        assertEquals(1, largestContiguousSum(arr));
    }
    @Test
    void KadaneAlgorithmTest3() {
        int arr[] = {1, -2};
        assertEquals(1, largestContiguousSum(arr));
    }
    @Test
    void KadaneAlgorithmTest4() {
        int arr[] = {1};
        assertEquals(1, largestContiguousSum(arr));
    }
    @Test
    void KadaneAlgorithmTest5() {
        int arr[] = {-1};
        assertEquals(-1, largestContiguousSum(arr));
    }

}
