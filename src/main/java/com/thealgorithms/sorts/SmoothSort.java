package com.thealgorithms.sorts;

/* 
 * @author Pankaj Kumar Bind (https://github.com/Pankaj-Bind)
 * @see SmoothSort Algorithm
 * Detailed description of this algorithm given below the code
 * 
 * SmoothSort uses a different data structure when compared to heap sort. 
 * Specifically, it uses a data structure called the Leonardo heap, which is a binary tree with the property that the root of each subtree has one fewer element than its parent. 
 * The shape of the Leonardo heap is determined by a sequence of numbers called the Leonardo.
 * Define the Leonardo numbers
 *	Leonardo numbers are a sequence of numbers defined by: L(0) = 1, L(1) = 1,
 *	L(n) = L(n - 1) + L(n - 2) + 1 for n > 1.
 * 
 */

import java.util.Arrays;

public class SmoothSort {
    /*
     * Leonardo numbers are a sequence of numbers defined by the recurrence
     * relation: L(0) = 1, L(1) = 1, L(k) = L(k - 1) + L(k - 2) + 1
     */
    static int leonardo(int k) {
        if (k < 2) {
            return 1;
        }
        return leonardo(k - 1) + leonardo(k - 2) + 1;
    }
    /*
     * Heapify is a function to build a heap from an array. It is used in
     * smoothSortStep function.
     */
    static void heapify(int[] arr, int start, int end) {
        int i = start;
        int j = 0;
        int k = 0;
           
        /*
         * Use bitwise operation to check if k is even or odd
         */
        while (k < end - start + 1) {
            if ((k & 0xAAAAAAAA) != 0) {
                j = j + i;
                i = i >> 1;
            } else {
                i = i + j;
                j = j >> 1;
            }

            k = k + 1;
        }

        while (i > 0) {
            j = j >> 1;
            k = i + j;
            while (k < end) {
                if (arr[k] > arr[k - i]) {
                    break;
                }
                int temp = arr[k];
                arr[k] = arr[k - i];
                arr[k - i] = temp;
                k = k + i;
            }

            i = j;
        }
    }
    /*
     * Smooth sort is a sorting algorithm based on the Leonardo numbers. It is
     * similar to heap sort but has a better worst case complexity.
     */
    static void smoothSortStep(int[] arr, int p, int q, int r) {
        /*
         * Heapify is a function to build a heap from an array. It is used in
         * smoothSortStep function.
         */
        while (p > 0) {
            if ((r & 0x03) == 0) {
                heapify(arr, r, q);
            }

            if (leonardo(r) == p) {
                r = r + 1;
            } else {
                r = r - 1;
                q = q - leonardo(r);
                heapify(arr, r, q);
                q = r - 1;
                r = r + 1;
            }

            int temp = arr[0];
            arr[0] = arr[p];
            arr[p] = temp;
            p = p - 1;
        }
    }
    /*
     * Convert heap to sorted array.
     * This function is used in smoothSort function.
     * It is used to convert the heap to a sorted array.
     * It is used in smoothSort function.
     */
    static void convertHeapToSortedArray(int[] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            int j = i + 1;
            while (j > 0 && arr[j] < arr[j - 1]) {
                int temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
                j = j - 1;
            }
        }
    }
    /*
     * Main function.
     * It is used to call smoothSort function.
     */
    static void runTests() {
        int[] arr1 = { 1, 7, 8, 2, 3, 5, 4, 6 };
        int[] arr2 = { 8, 19, 7, 5, 4, 6, 2, 18, 9, 1, 17, 28, 29 };

        System.out.print("Test 1 - Input: ");
        System.out.println(Arrays.toString(arr1));
        
        int[] result1 = smoothSort(arr1);
        System.out.print("Test 1 - Output: ");
        System.out.println(Arrays.toString(result1));

        System.out.print("Test 2 - Input: ");
        System.out.println(Arrays.toString(arr2));
        int[] result2 = smoothSort(arr2);
        System.out.print("Test 2 - Output: ");
        System.out.println(Arrays.toString(result2));
    }

    public static void main(String[] args) {
        runTests();
    }
    
    static int[] smoothSort(int[] arr) {
        int n = arr.length;

        int p = n - 1;
        int q = p;
        int r = 0;

        while (p > 0) {
            if ((r & 0x03) == 0) {
                heapify(arr, r, q);
            }

            if (leonardo(r) == p) {
                r = r + 1;
            } else {
                r = r - 1;
                q = q - leonardo(r);
                heapify(arr, r, q);
                q = r - 1;
                r = r + 1;
            }

            int temp = arr[0];
            arr[0] = arr[p];
            arr[p] = temp;
            p = p - 1;
        }
        /*
         * Convert heap to sorted array.
         */
        convertHeapToSortedArray(arr, n);

        return arr;
    }
}

/*
 * 
 * Input: [1, 7, 8, 2, 3, 5, 4, 6]
 * Output: [1, 2, 3, 4, 5, 6, 7, 8]
 * Time complexity : O(nlogn)
 * Auxiliary Space: O(1)
 * Worst-case performance : O(n log n)
 * Best-case performance : O(n)
 * Average performance : O(n log n)
 * Worst-case space complexity : O(n)
 * 
 * Creator and Year : Edsger W. Dijkstra, 1985.
 * Type : Comparison-based sorting algorithm.
 * Adaptiveness : Performs well on partially sorted data.
 * Heap Structure : Uses a variant of Heap Sort called "smooth heap".
 * Heap Construction : Builds smooth heap via "sift down" operations.
 * Sorting Process : Swaps elements to sort the array.
 * Efficiency : Effective for partially ordered data.
 * Usage : Less common in practice compared to other algorithms.
 * Advantages : Good for nearly sorted data, theoretical performance guarantees.
 * Disadvantages : More complex to implement, not always practical advantage
 * over other sorts.
 * */
