package com.thealgorithms.searches;

import com.thealgorithms.devutils.searches.SearchAlgorithm;

/*
 *  Fibonacci Search is a popular algorithm which finds the position of a target value in
 *  a sorted array
 *
 *  The time complexity for this search algorithm is O(log3(n))
 *  The space complexity for this search algorithm is O(1)
 *  @author Kanakalatha Vemuru (https://github.com/KanakalathaVemuru)
 */
public class FibonacciSearch implements SearchAlgorithm {

    /**
     * @param array is a sorted array where the element has to be searched
     * @param key   is an element whose position has to be found
     * @param <T>   is any comparable type
     * @return index of the element
     */
    @Override
    public <T extends Comparable<T>> int find(T[] array, T key) {
        int fibMinus1 = 1; // (m-1)'th Fibonacci No.
        int fibMinus2 = 0; // (m-2)'th Fibonacci No.
        int fibNumber = fibMinus1 + fibMinus2; // m'th Fibonacci
        int n = array.length;

        // fibNumber is going to store the smallest Fibonacci
        // Number less than n
        while (fibNumber < n) {
            fibMinus2 = fibMinus1;
            fibMinus1 = fibNumber;
            fibNumber = fibMinus2 + fibMinus1;
        }

        int offset = -1; // Marks the eliminated range from front

        while (fibNumber > 1) {
            int i = Math.min(offset + fibMinus2, n - 1);

            if (array[i].compareTo(key) < 0) {
                // Search in the right side of the array
                fibNumber = fibMinus1;
                fibMinus1 = fibMinus2;
                fibMinus2 = fibNumber - fibMinus1;
                offset = i;
            } else if (array[i].compareTo(key) > 0) {
                // Search in the left side of the array
                fibNumber = fibMinus2;
                fibMinus1 = fibMinus1 - fibMinus2;
                fibMinus2 = fibNumber - fibMinus1;
            } else {
                return i; // Element found at index i
            }
        }

        // comparing the last element with key
        if (fibMinus1 == 1 && array[offset + 1] == key) {
            return offset + 1;
        }

        return -1; // Element not found. Return -1
    }

    // Driver Program
    public static void main(String[] args) {
        Integer[] integers = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512};

        int size = integers.length;
        Integer shouldBeFound = 128;
        FibonacciSearch fsearch = new FibonacciSearch();
        int atIndex = fsearch.find(integers, shouldBeFound);

        System.out.println("Should be found: " + shouldBeFound + ". Found " + integers[atIndex] + " at index " + atIndex + ". An array length " + size);
    }
}
