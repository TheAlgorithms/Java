package com.thealgorithms.misc;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class Sort012DTest {
    @Test
    public static void emptyArraySort012() {
        int[] inputArray = {};
        int[] outputArray = Sort012D.sort012(inputArray);
        int[] expectedOutput = {};
        assertArrayEquals(outputArray, expectedOutput);
    }
    @Test
    public void sortSingleElementSort012() {
        int[] inputArray = {0};
        int[] outputArray = Sort012D.sort012(inputArray);
        int[] expectedOutput = {0};
        assertArrayEquals(outputArray, expectedOutput);
    }
    @Test
    public void sortIntegerArraySort012() {
        int [] inputArray = {0,1,1,0,0,2,1,0,0,2,2,1,0,1,2};
        int [] outputArray = Sort012D.sort012(inputArray);
        int [] expectedOutput = {0,0,0,0,0,0,1,1,1,1,1,2,2,2,2};
        assertArrayEquals(outputArray, expectedOutput);
    }
    @Test
    public static void emptyArrayApproach2() {
        int[] inputArray = {};
        int[] outputArray = Sort012D.approach2(inputArray);
        int[] expectedOutput = {};
        assertArrayEquals(outputArray, expectedOutput);
    }
    @Test
    public void sortSingleElementApproach2() {
        int[] inputArray = {0};
        int[] outputArray = Sort012D.approach2(inputArray);
        int[] expectedOutput = {0};
        assertArrayEquals(outputArray, expectedOutput);
    }
    @Test
    public void sortIntegerArrayAppraoch() {
        int [] inputArray = {0,1,1,0,0,2,1,0,0,2,2,1,0,1,2};
        int [] outputArray = Sort012D.approach2(inputArray);
        int [] expectedOutput = {0,0,0,0,0,0,1,1,1,1,1,2,2,2,2};
        assertArrayEquals(outputArray, expectedOutput);
    }
}