package com.thealgorithms.datastructures.arrays;

/**
 * A program to find leaders in an array.
 * 
 * A leader is an element that is greater than all the elements to its right.
 * The rightmost element is always a leader.
 *
 * Example:
 * Input: [16, 17, 4, 3, 5, 2]
 * Output: Leaders are 17, 5, 2
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 * 
 * Author: https://github.com/VeeruYadav45
 */
public class LeadersInArray {

    /**
     * Prints all leader elements in the array.
     *
     * @param arr the input array
     */
    public static void findLeaders(int[] arr) {
        int n = arr.length;

        // The rightmost element is always a leader
        int maxFromRight = arr[n - 1];
        System.out.print("Leaders: " + maxFromRight + " ");

        // Traverse the array from right to left
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > maxFromRight) {
                maxFromRight = arr[i]; // Update the new leader
                System.out.print(maxFromRight + " ");
            }
        }
    }

    // Example usage
    public static void main(String[] args) {
        int[] arr = { 16, 17, 4, 3, 5, 2 };
        findLeaders(arr);
    }
}
