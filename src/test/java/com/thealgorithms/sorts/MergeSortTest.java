package com.thealgorithms.sorts;

import org.junit.jupiter.api.Test;


import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author onglipwei
 * @create 2022-08-30 12:28 AM
 */
public class MergeSortTest {

    @Test
    public void testSort() {
        //initialize the mergeSort object
        MergeSort mergeSort = new MergeSort();

        //initialize the array to be sorted and expected array
        Integer[] arr = {4, 23, 6, 78, 1, 54, 231, 9, 12};
        Integer[] expectedArr = {1, 4, 6, 9, 12, 23, 54, 78, 231};

        //initialize the array to be sorted and expected array
        String[] stringArray = {"c", "a", "e", "b", "d"};
        String[] expectedStringArray = {"a", "b", "c", "d", "e"};

        //apply sorting algorithms
        mergeSort.sort(arr);
        mergeSort.sort(stringArray);

        //unit testing
        assertArrayEquals(arr, expectedArr);
        assertArrayEquals(stringArray, expectedStringArray);

    }


}
