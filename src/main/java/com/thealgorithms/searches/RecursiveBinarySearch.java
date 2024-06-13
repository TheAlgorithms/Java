// Code by Pronay Debnath
// Created:- 1/10/2023
// File Name should be RecursiveBinarySearch.java
// Explanation:- https://www.tutorialspoint.com/java-program-for-binary-search-recursive
package com.thealgorithms.searches;

import java.util.Scanner;

// Create a SearchAlgorithm class with a generic type
abstract class SearchAlgorithm<T extends Comparable<T>> {
    // Abstract find method to be implemented by subclasses
    public abstract int find(T[] arr, T target);
}

public class RecursiveBinarySearch<T extends Comparable<T>> extends SearchAlgorithm<T> {

    // Override the find method as required
    @Override
    public int find(T[] arr, T target) {
        // Call the recursive binary search function
        return binsear(arr, 0, arr.length - 1, target);
    }

    // Recursive binary search function
    public int binsear(T[] arr, int left, int right, T target) {
        if (right >= left) {
            int mid = left + (right - left) / 2;

            // Compare the element at the middle with the target
            int comparison = arr[mid].compareTo(target);

            // If the element is equal to the target, return its index
            if (comparison == 0) {
                return mid;
            }

            // If the element is greater than the target, search in the left subarray
            if (comparison > 0) {
                return binsear(arr, left, mid - 1, target);
            }

            // Otherwise, search in the right subarray
            return binsear(arr, mid + 1, right, target);
        }

        // Element is not present in the array
        return -1;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            // User inputs
            System.out.print("Enter the number of elements in the array: ");
            int n = sc.nextInt();

            Integer[] a = new Integer[n]; // You can change the array type as needed

            System.out.println("Enter the elements in sorted order:");

            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }

            System.out.print("Enter the target element to search for: ");
            int t = sc.nextInt();

            RecursiveBinarySearch<Integer> searcher = new RecursiveBinarySearch<>();
            int res = searcher.find(a, t);

            if (res == -1) {
                System.out.println("Element not found in the array.");
            } else {
                System.out.println("Element found at index " + res);
            }
        }
    }
}
