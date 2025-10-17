package com.thealgorithms.datastructures.arrays;

/**
 * A program to find leaders in an array.
 *
 * <p>
 * A leader is an element that is greater than all the elements to its right.
 * The rightmost element is always a leader.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Author: https://github.com/VeeruYadav45
 */
public final class LeadersInArray {

    // Prevent instantiation of this utility class
    private LeadersInArray() {
    }

    /**
     * Prints all leader elements in the array.
     *
     * @param arr the input array
     */
    public static void findLeaders(final int[] arr) {
        int n = arr.length;
        int maxFromRight = arr[n - 1];
        System.out.print("Leaders: " + maxFromRight + " ");

        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > maxFromRight) {
                maxFromRight = arr[i];
                System.out.print(maxFromRight + " ");
            }
        }
        System.out.println();
    }

    /**
     * Example usage.
     *
     * @param args command line arguments (not used)
     */
    public static void main(final String[] args) {
        int[] arr = {16, 17, 4, 3, 5, 2};
        findLeaders(arr);
    }
}
