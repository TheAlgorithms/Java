package com.company;


/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 */

public class MedianSortedArray {

    /**
     * Calculate average median of two sorted array
     *
     * @param nums1 first sorted array
     * @param nums2 second sorted array
     * @return median of merged arrays as a double
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m;
        int i = 0, j = 0, idx = 0;
        double[] arr = new double[nums1.length + nums2.length];
        //merge arrays
        for (; i < nums1.length && j < nums2.length; ) {
            if (nums2[j] > nums1[i]) {
                arr[idx] = nums1[i];
                i++;
            } else {
                arr[idx] = nums2[j];
                j++;
            }
            idx++;
        }
        //in case of end of one array fill it in with values from other
        if (i == nums1.length) {
            for (int k = j; idx < arr.length; k++) {
                arr[idx++] = nums2[k];
            }
        } else if (j == nums2.length) {
            for (int k = i; idx < arr.length; k++) {
                arr[idx++] = nums1[k];
            }
        }
        //find median
        if (arr.length % 2 != 0) {
            m = arr.length / 2;
            return arr[m];
        } else {
            m = arr.length / 2;
            return (arr[m] + arr[m - 1]) / 2;
        }
    }
}
