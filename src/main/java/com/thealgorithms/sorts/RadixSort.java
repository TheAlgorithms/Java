package com.thealgorithms.sorts;

import java.util.Arrays;

/**
 * @author James Mc Dermott(theycallmemac)
 * @see https://www.geeksforgeeks.org/radix-sort/
 * @see https://www.youtube.com/watch?v=XiuSW_mEn7g
 * 
 * Radix sort is an algorithm that uses counting sort as a subroutine to sort an array of only either positive or negetive integers/strings 
 * in either ascending or descending order.
 * The main idea of radix sort revolves around applying counting sort digit by digit on the given array.
 */

class RadixSort {

    /*
     * This method returns the maximum value in the array
     * @param arr The array where to find the maximum value
     */
    private int getMax(int[] arr) {
        int mx = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > mx) {
                mx = arr[i];
            }
        }
        return mx;
    }

    /*
     * This method implements the counting sort algorithm according to the digit represented by exp.
     * @param arr The array to be sorted
     * @param exp The exponent to be used in the counting sort algorithm
     */
    private void countSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    public void radixSort(int[] arr) {
        // Find the maximum number to know number of digits
        int max = getMax(arr);

        // Do counting sort for every digit. Note that
        // instead of passing digit number, exp is passed.
        // exp is 10^i where i is current digit number
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(arr, exp);
        }
    }

    public static void main(String[] args) {
        int[] arr = { };
        int n = arr.length;
        RadixSort rs = new RadixSort();
        rs.radixSort(arr);
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
