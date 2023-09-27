package com.thealgorithms.sorts;

/**
 * @author Pankaj Kumar Bind (https://github.com/Pankaj-Bind)
 * @see SmoothSort Algorithm
 * Detailed description of this algorithm given below the code
 */

import java.util.Arrays;

public class SmoothSort {

	// Define the Leonardo numbers
    // Leonardo numbers are a sequence of numbers defined by: L(0) = 1, L(1) = 1, L(n) = L(n - 1) + L(n - 2) + 1 for n > 1.
	static int leonardo(int k)
	{
		if (k < 2) {
			return 1;
		}
		return leonardo(k - 1) + leonardo(k - 2) + 1;
	}

	// Build the Leonardo heap by merging
	// pairs of adjacent trees
	static void heapify(int[] arr, int start, int end)
	{
		int i = start;
		int j = 0;
		int k = 0;

		while (k < end - start + 1) {
			if ((k & 0xAAAAAAAA) != 0) {
				j = j + i;
				i = i >> 1;
			}
			else {
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

	// Smooth Sort function 
	static int[] smoothSort(int[] arr)
	{
		int n = arr.length;

		int p = n - 1;
		int q = p;
		int r = 0;

		// Build the Leonardo heap by merging 
		// pairs of adjacent trees 
		while (p > 0) {
			if ((r & 0x03) == 0) {
				heapify(arr, r, q);
			}

			if (leonardo(r) == p) {
				r = r + 1;
			}
			else {
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

		// Convert the Leonardo heap 
		// back into an array 
		for (int i = 0; i < n - 1; i++) {
			int j = i + 1;
			while (j > 0 && arr[j] < arr[j - 1]) {
				int temp = arr[j];
				arr[j] = arr[j - 1];
				arr[j - 1] = temp;
				j = j - 1;
			}
		}

		return arr;
	}

	public static void main(String[] args)
	{
		int[] arr = { 8, 19, 7, 5, 4, 6, 2, 18, 9, 1, 17, 28, 29};

		// Original Array 
		System.out.print("Input: ");
		System.out.println(Arrays.toString(arr));

		// Function call
		arr = smoothSort(arr);

		// Sorted Array
		System.out.print("Output: ");
		System.out.println(Arrays.toString(arr));
	}
}

/* 

Input:    [1, 7, 8, 2, 3, 5, 4, 6]
Output:   [1, 2, 3, 4, 5, 6, 7, 8]
Time complexity : O(nlogn)
Auxiliary Space: O(1)
Worst-case performance : O(n log n)
Best-case performance : O(n)
Average performance :	O(n log n)
Worst-case space complexity : O(n)

Creator and Year 		: Edsger W. Dijkstra, 1985.
Type					: Comparison-based sorting algorithm.
Adaptiveness			: Performs well on partially sorted data.
Heap Structure			: Uses a variant of Heap Sort called "smooth heap".
Heap Construction		: Builds smooth heap via "sift down" operations.
Sorting Process			: Swaps elements to sort the array.
Efficiency				: Effective for partially ordered data.
Usage					: Less common in practice compared to other algorithms.
Advantages				: Good for nearly sorted data, theoretical performance guarantees.
Disadvantages			: More complex to implement, not always practical advantage over other sorts.

*/