package com.thealgorithms.maths;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MedianTest {
    @Test
        //Single value case
    void medianSingleValue() {
        //0
        int[] arr = {0};
        assertEquals(0, Median.median(arr));
    }

    @Test
        //Two values case
    void medianTwoValues() {
        //1,2
        int[] arr = {1, 2};
        assertEquals(1.5, Median.median(arr));
    }

    @Test
        //Three values case
    void medianThreeValues() {
        //1,2,3
        int[] arr = {1, 2, 3};
        assertEquals(2, Median.median(arr));
    }

    @Test
        //Many values case
    void medianDecimalValueReturn() {
        //1, 2, 3, 4, 5, 6, 8, 9
        int[] arr = {1, 2, 3, 4, 5, 6, 8, 9};
        assertEquals(4.5, Median.median(arr));
    }

    @Test
        //Negative value case
    void medianNegativeValues() {
        //-27, -16, -7, -4, -2, -1
        int[] arr = {-27, -16, -7, -4, -2, -1};
        assertEquals(-5.5, Median.median(arr));
    }

    @Test
        //Unsorted values case
    void medianUnsortedValues() {
        //before sorting : 16, 3, 8, 12, 13, 1, 5
        //after sorting : 1, 3, 5, 8, 12, 13, 16
        int[] arr = {16, 3, 8, 12, 13, 1, 5};
        assertEquals(8, Median.median(arr));
    }
}
