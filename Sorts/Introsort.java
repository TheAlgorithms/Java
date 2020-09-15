// Java implementation of Introsort algorithm 
package Sorts;

import static Sorts.SortUtils.*;
import java.io.IOException;

public class Introsort implements SortAlgorithm {

	static <T extends Comparable<T>> void maxHeap(T[] array, int i, int heapN, int begin) {
		T temp = array[begin + i - 1];
		int child;

		while (i <= heapN / 2) {
			child = 2 * i;

			if (child < heapN && array[begin + child - 1].compareTo(array[begin + child]) < 0)
				child++;

			if (temp.compareTo(array[begin + child - 1]) >= 0)
				break;

			array[begin + i - 1] = array[begin + child - 1];
			i = child;
		}
		array[begin + i - 1] = temp;
	}

	// Function to build the heap (rearranging the array)
	static <T extends Comparable<T>> void heapify(T[] array, int begin, int end, int heapN) {
		for (int i = (heapN) / 2; i >= 1; i--)
			maxHeap(array, i, heapN, begin);
	}

	// main function to do heapsort
	static <T extends Comparable<T>> void heapSort(T[] array, int begin, int end) {
		int heapN = end - begin;

		// Build heap (rearrange array)
		heapify(array, begin, end, heapN);

		// One by one extract an element from heap
		for (int i = heapN; i >= 1; i--) {

			// Move current root to end
			swap(array, begin, begin + i);

			// call maxHeap() on the reduced heap
			maxHeap(array, 1, i, begin);
		}
	}

	// function that implements insertion sort
	static <T extends Comparable<T>> void insertionSort(T[] array, int left, int right) {

		for (int i = left; i <= right; i++) {
			T key = array[i];
			int j = i;

			// Move elements of arr[0..i-1], that are
			// greater than the key, to one position ahead
			// of their current position
			while (j > left && array[j - 1].compareTo(key) > 0) {
				array[j] = array[j - 1];
				j--;
			}
			array[j] = key;
		}
	}

	static <T extends Comparable<T>> T minimum(T v, T w) {
		return (v.compareTo(w) < 0) ? v : w;
	}

	static <T extends Comparable<T>> T maximum(T v, T w) {
		return (v.compareTo(w) > 0) ? v : w;
	}

	// Function for finding the median of the three elements
	static <T extends Comparable<T>> int findPivot(T[] array, int a1, int b1, int c1) {
		T max = maximum(maximum(array[a1], array[b1]), array[c1]);
		// Math.max(Math.max(a[a1], a[b1]), a[c1]);
		T min = minimum(minimum(array[a1], array[b1]), array[c1]);
		// T min = Math.min(Math.min(a[a1], a[b1]), a[c1]);
		int median = ((int) max ^ (int) array[a1] ^ (int) array[b1] ^ (int) array[c1]);
		if (median == (int) array[a1])
			return a1;
		if (median == (int) array[b1])
			return b1;
		return c1;
	}

	// This function takes the last element as pivot, places
	// the pivot element at its correct position in sorted
	// array, and places all smaller (smaller than pivot)
	// to the left of the pivot
	// and greater elements to the right of the pivot
	static <T extends Comparable<T>> int partition(T[] array, int low, int high) {

		// pivot
		T pivot = array[high];

		// Index of smaller element
		int i = (low - 1);
		for (int j = low; j <= high - 1; j++) {

			// If the current element is smaller
			// than or equal to the pivot
			// System.out.println(i);
			if (array[j].compareTo(pivot) <= 0) {

				// increment index of smaller element
				i++;
				swap(array, i, j);
			}
		}
		swap(array, i + 1, high);
		return (i + 1);
	}

	// The main function that implements Introsort
	// low --> Starting index,
	// high --> Ending index,
	// depthLimit --> recursion level
	static <T extends Comparable<T>> void sortDataUtil(T[] array, int begin, int end, int depthLimit) {
		if (end - begin > 16) {
			if (depthLimit == 0) {

				// if the recursion limit is
				// occurred call heap sort
				heapSort(array, begin, end);
				return;
			}

			depthLimit = depthLimit - 1;
			int pivot = findPivot(array, begin, begin + ((end - begin) / 2) + 1, end);
			swap(array, pivot, end);

			// p is partitioning index,
			// arr[p] is now at right place
			// System.out.println("b " + begin + " end " + end);
			int p = partition(array, begin, end);

			// Separately sort elements before
			// partition and after partition
			sortDataUtil(array, begin, p - 1, depthLimit);
			sortDataUtil(array, p + 1, end, depthLimit);
		}

		else {
			// if the data set is small,
			// call insertion sort
			insertionSort(array, begin, end);
		}
	}

	// private static < T > void printArray(T []array) {
	// for (int i = 0; i < array.length; i++)
	// System.out.print(array[i] + " ");
	// }

	@Override
	public <T extends Comparable<T>> T[] sort(T[] array) {
		int n = array.length;
		int depthLimit = (int) (2 * Math.floor(Math.log(n) / Math.log(2)));

		sortDataUtil(array, 0, n - 1, depthLimit);
		return array;
	}

	// Driver code
	public static void main(String args[]) throws IOException {

		Introsort introsort = new Introsort();
		Integer[] array = { 2, 10, 24, 2, 10, 11, 27, 4, 2, 4, 28, 16, 9, 8, 28, 10, 13, 24, 22, 28, 0, 13, 27, 13, 3,
				23, 18, 22, 8, 8, 99 };

		
		print(introsort.sort(array));
		// OUTPUT
		// [0, 2, 2, 2, 3, 4, 4, 8, 8, 8, 9, 10, 10, 10, 11, 13, 13, 13, 16, 18, 22, 22, 23, 24, 24, 27, 27, 28, 28, 28, 99]

		Character arr[]={'k','t','s','a','g','f','q','k','h','i','c','b'};
		print(introsort.sort(arr));
		// OUTPUT
		// [a, b, c, f, g, h, i, k, k, q, s, t]

		


		// introsort.printData();
	}

}
