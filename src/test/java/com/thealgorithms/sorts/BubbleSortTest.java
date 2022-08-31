package com.thealgorithms.sorts;

import org.junit.jupiter.api.Test;


import java.util.Arrays;

import static com.thealgorithms.sorts.SortUtils.print;
import static org.junit.jupiter.api.Assertions.*;
/**
 * @author onglipwei
 * @create 2022-08-30 12:41 AM
 */

public class BubbleSortTest {

    @Test
    public void bubbleSortTest(){

        //initialize the insertionsort object
        BubbleSort bubbleSort = new BubbleSort();

        //initialize the array to be sorted and expected array
        Integer[] arr = {4, 23, 6, 78, 1, 54, 231, 9, 12};
        Integer[] expectedArr = {1, 4, 6, 9, 12, 23, 54, 78, 231};

        //initialize the array to be sorted and expected array
        String[] stringArray = {"c", "a", "e", "b", "d"};
        String[] expectedStringArray = {"a", "b", "c", "d", "e"};

        //apply sorting algorithms
        bubbleSort.sort(arr);
        bubbleSort.sort(stringArray);

        //unit testing
        assertArrayEquals(arr, expectedArr);
        assertArrayEquals(stringArray, expectedStringArray);


    }

}
