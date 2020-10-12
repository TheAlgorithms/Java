package com.search;

import static java.lang.Math.min;

/**
 * Fibonacci search is a method of searching a sorted array using a divide and conquer algorithm that narrows down
 * possible locations with the aid of Fibonacci numbers. Compared to binary search where the sorted array is divided
 * into two equal-sized parts, one of which is examined further, Fibonacci search divides the array into two parts that
 * have sizes that are consecutive Fibonacci numbers.
 * 
 * Worst-case performance	O(Log n)
 * Best-case performance	O(1)
 * Average performance	O(Log n)
 * Average space complexity	O(1)
 */
public class FibonacciSearch {
    /**
     * @param array is an array where the element should be found
     * @param key   is an element which should be found
     * @param <T>   is any comparable type
     * @return The index position of the key in the array, returns -1 for empty array
     */
    public <T extends Comparable<T>> int findIndex(T[] array, T key) {
        int size = array.length;

        if (size == 0)
            return -1;

        // Initialize the fibonacci numbers
        int fibN1 = 1; // (n-1)th Fibonacci term
        int fibN2 = 0; // (n-2)th Fibonacci term
        int fibN = fibN1 + fibN2; // nth Fibonacci term

        // fibN should store the smallest Fibonacci Number greater than or equal to size
        while (fibN < size) {
            fibN2 = fibN1;
            fibN1 = fibN;
            fibN = fibN2 + fibN1;
        }

        // Marks the eliminated range from front
        int offset = -1;

        while (fibN > 1) {
            // Check if fibN2 is a valid location
            int i = min(offset + fibN2, size - 1);

            // If key is greater than the value at index fibN2, cuts the sub-array from offset to i
            if (array[i].compareTo(key) < 0) {
                fibN = fibN1;
                fibN1 = fibN2;
                fibN2 = fibN - fibN1;
                offset = i;
            }

            // If x is greater than the value at index fibN2, cuts the sub-array after i+1
            else if (array[i].compareTo(key) > 0) {
                fibN = fibN2;
                fibN1 = fibN1 - fibN2;
                fibN2 = fibN - fibN1;
            } else return i; // Element found
        }
        // comparing the last element with key
        if (fibN1 == 1 && array[offset + 1].compareTo(key) == 0)
            return offset + 1;

        return -1; // Element not found
    }
}
