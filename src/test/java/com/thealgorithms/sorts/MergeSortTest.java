package com.thealgorithms.sorts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortTest {

    private MergeSort mergeSort = new MergeSort();


    @Test
    void emptyListShouldPass(){
        Integer[] array = {};
        Integer[] sorted = mergeSort.sort(array);
        Integer[] expected = {};
        assertArrayEquals(expected, sorted);
    }

    @Test
    void mergeSortSingleValueArrayShouldPass()
    {
        Integer[] array = {7};
        Integer[] sorted = mergeSort.sort(array);
        Integer[] expected = {7};
        assertArrayEquals(expected, sorted);
    }

    @Test
    void mergeSortWithIntegerArrayShouldPass()
    {
        Integer[] array = {49,4,36,9,144,1};
        Integer[] sorted = mergeSort.sort(array);
        Integer[] expected = {1,4,9,36,49,144};
        assertArrayEquals(expected, sorted);
    }

    @Test
    void mergeSortForArrayWithNegativeValuesShouldPass()
    {
        Integer[] array = {49,-36,-144,-49,1,9};
        Integer[] sorted = mergeSort.sort(array);
        Integer[] expected = {-144,-49,-36,1,9,49};
        assertArrayEquals(expected, sorted);
    }

    @Test
    void mergeSortForArrayWithDuplicateValuesShouldPass()
    {
        Integer[] array = {36,1,49,1,4,9};
        Integer[] sorted = mergeSort.sort(array);
        Integer[] expected = {1,1,4,9,36,49};
        assertArrayEquals(expected, sorted);
    }

    @Test
    void mergeSortWithStringArrayShouldPass()
    {
        String[] array = {"c", "a", "e", "b", "d"};
        String[] sorted = mergeSort.sort(array);
        String[] expected = {"a","b","c","d","e"};
        assertArrayEquals(expected, sorted);
    }

}
