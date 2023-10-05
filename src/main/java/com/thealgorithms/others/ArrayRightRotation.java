package com.thealgorithms.others;

/*
 * A right rotation operation on an array
 * shifts each of the array's elements
 * given integer n unit to the right.
 *
 * @author sangin-lee
 */

public class ArrayLeftRotation {

    /*
     * Returns the result of right rotation of given array arr and integer n
     *
     * @param arr : int[] given array
     *
     * @param n : int given integer
     *
     * @return : int[] result of right rotation
     */
    public static int[] rotateRight(int[] arr, int n) {
        int size = arr.length;
        int[] dst = new int[size];
        n = n % size;
        for (int i = 0; i < size; i++) {
            dst[n] = arr[i];
            n = (n + 1) % size;
        }
        return dst;
    }
}
