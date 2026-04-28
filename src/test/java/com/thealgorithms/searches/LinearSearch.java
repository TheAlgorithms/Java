package com.thealgorithms.searches;

/*
 * Linear Search Algorithm
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

public class LinearSearch {

    // Method to perform linear search
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // element found
            }
        }
        return -1; // element not found
    }

    // Main method for testing
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};
        int target = 30;

        int result = linearSearch(arr, target);

        if (result != -1) {
            System.out.println("Element found at index: " + result);
        } else {
            System.out.println("Element not found");
        }
    }
}