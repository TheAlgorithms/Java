package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedianSortedArrayTest {

    /*
    * Constrains:
    * nums1.length == m
    * nums2.length == n
    * 0 <= m <= 1000
    * 0 <= n <= 1000
    * 1 <= m + n <= 2000
    * -106 <= nums1[i], nums2[i] <= 106
    */

    @Test
    //Unique values case
    void medianSortedArraysWithoutRepetition() {
        //1,2,3,6,8,9,10,11
        int[] arr1 = {1,3,6,8,10};
        int[] arr2 = {2,9,11};
         assertEquals(7, MedianSortedArray.findMedianSortedArrays(arr1, arr2));
    }
    @Test
    // Repetition values case
    void medianSortedArraysWithRepetition() {
        //1,1,1,1,10,10,10,10,10,10,10
        int[] arr1 = {1,1,1,10,10,10,10,10};
        int[] arr2 = {1,10,10};
        assertEquals(10, MedianSortedArray.findMedianSortedArrays(arr1, arr2));
    }
    @Test
    //Return decimal value case
    void medianSortedArraysWithDecimalValue() {
        //1,2,5,7,8,9,10,11
        int[] arr1 = {1,5,7,8};
        int[] arr2 = {2,9,10,11};
        assertEquals(7.5, MedianSortedArray.findMedianSortedArrays(arr1, arr2));
    }
    @Test
    //Return negative value case
    void medianSortedArraysWithNegativeValue() {
        //-11,-10,-9,-8,-7,-5,-2,-1
        int[] arr1 = {-7,-5,-2,-1};
        int[] arr2 = {-11,-10,-9,-8};
        assertEquals(-7.5, MedianSortedArray.findMedianSortedArrays(arr1, arr2));
    }
    @Test
    //One empty array case
    void medianSortedArraysWithOneEmptyArray() {
        //3,6
        int[] arr1 = {3,6};
        int[] arr2 = {};
        assertEquals(4.5, MedianSortedArray.findMedianSortedArrays(arr1, arr2));
    }
}