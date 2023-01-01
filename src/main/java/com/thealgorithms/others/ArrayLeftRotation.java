package com.thealgorithms.others;

/*
 * A left rotation operation on an array
 * shifts each of the array's elements
 * given integer n unit to the left.
 *
 * @author sangin-lee
 */

public class ArrayLeftRotation {

    /*
     * Returns the result of left rotation of given array arr and integer n
     *
     * @param arr : int[] given array
     *
     * @param n : int given integer
     *
     * @return : int[] result of left rotation
     */
    public static int[] rotateLeft(int[] arr, int n) {
    int size = arr.length;
    int[] dst = new int[size];
    n = n % size;
    System.arraycopy(arr, n, dst, 0, size - n);
    System.arraycopy(arr, 0, dst, size - n, n);
    return dst;
  }
}
