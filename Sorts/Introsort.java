package Sorts;
import static Sorts.SortUtils.*;
import java.io.IOException;

/**
 * Into Sort is a hybrid sorting algorithm. It is a combination of Heap Sort,Merge Sort, and Insertion Sort
 * Hence these three algorithms are the pre-requisites for Intro Sort
 *
 * @author Shivam Anand (https://github.com/anandshivam44)
 */

public class Introsort implements SortAlgorithm {

	/**
	 * Java implementation of maxHeap
	 * 
	 * To maxHeap a subtree rooted with node i which is an index in a[]. heapN is size of heap 
	 * @param size is size of array
	 * 
	 *  */ 
	static <T extends Comparable<T>> void maxHeap(T[] array, int i, int size, int begin) {
		T temp = array[begin + i - 1];
		int child;

		while (i <= size / 2) {
			child = 2 * i;

			if (child < size && array[begin + child - 1].compareTo(array[begin + child]) < 0)
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

	// function that performs heap sort
	static <T extends Comparable<T>> void heapSort(T[] array, int begin, int end) {
		int heapN = end - begin;

		// make heap
		heapify(array, begin, end, heapN);

		// One by one extract an element from heap
		for (int i = heapN; i >= 1; i--) {

			// Move current root to end
			swap(array, begin, begin + i);

			// call maxHeap() on the reduced heap
			maxHeap(array, 1, i, begin);
		}
	}

	/**
	 * This function implements the standard Insertion Sort
	 */
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

	//custom method to find minimum
	static <T extends Comparable<T>> T minimum(T v, T w) {
		return (v.compareTo(w) < 0) ? v : w;
	}

	//custom method to find maximum
	static <T extends Comparable<T>> T maximum(T v, T w) {
		return (v.compareTo(w) > 0) ? v : w;
	}

	/**
	 * This function finds the median of 3
	 */
	static <T extends Comparable<T>> int findPivot(T[] array, int a1, int b1, int c1) {
		T max = maximum(maximum(array[a1], array[b1]), array[c1]);
		T min = minimum(minimum(array[a1], array[b1]), array[c1]);
		int median = ((int) max ^ (int) min ^ (int) array[a1] ^ (int) array[b1] ^ (int) array[c1]);
		if (median == (int) array[a1])
			return a1;
		if (median == (int) array[b1])
			return b1;
		return c1;
	}

	/**
	 * This function takes the last element as pivot, places the pivot element at
	 * its correct position in sorted array, and places all smaller (smaller than
	 * pivot) to the left of the pivot and greater elements to the right of the
	 * pivot
	 */
	static <T extends Comparable<T>> int partition(T[] array, int low, int high) {

		// pivot
		T pivot = array[high];

		// Index of smaller element
		int i = (low - 1);
		for (int j = low; j <= high - 1; j++) {

			if (array[j].compareTo(pivot) <= 0) { // If the current element is smaller than or equal to the pivot then

				// increment index of smaller element
				i++;
				swap(array, i, j);
			}
		}
		swap(array, i + 1, high);
		return (i + 1);
	}

	/**
	 * This is the main implementation of Intro Sort
	 * 
	 * @param array      The array in which sorting has to be performed
	 * @param begin      starting index of array
	 * @param end        last index of array
	 * @param depthLimit recursion level or the height till which recursion has to
	 *                   be performed
	 */
	static <T extends Comparable<T>> void ImplementationIntroSort(T[] array, int begin, int end, int depthLimit) {
		if (end - begin > 16) {
			if (depthLimit == 0) {

				// if the recursion limit is reached start Heap Sort
				heapSort(array, begin, end);
				return;
			}

			depthLimit = depthLimit - 1;
			int pivot = findPivot(array, begin, begin + ((end - begin) / 2) + 1, end);
			swap(array, pivot, end);

			// p is the index at which the array will be partioned
			int p = partition(array, begin, end);

			// partition the array and perform individual sorting
			ImplementationIntroSort(array, begin, p - 1, depthLimit);
			ImplementationIntroSort(array, p + 1, end, depthLimit);
		}

		else {
			// If the data set is small go for Insertion Sort
			insertionSort(array, begin, end);
		}
	}

	@Override
	public <T extends Comparable<T>> T[] sort(T[] array) {
		int n = array.length;
		int depthLimit = (int) (2 * Math.floor(Math.log(n) / Math.log(2)));

		ImplementationIntroSort(array, 0, n - 1, depthLimit);
		return array;
	}

	public static void main(String args[]) throws IOException {

		Introsort introsort = new Introsort();
		Integer[] array = { 2, 10, 24, 2, 10, 11, 27, 4, 2, 4, 28, 16, 9, 8, 28, 10, 13, 24, 22, 28, 0, 13, 27, 13, 3,
				23, 18, 22, 8, 8, 99 };

		print(introsort.sort(array));
		// OUTPUT
		// [0, 2, 2, 2, 3, 4, 4, 8, 8, 8, 9, 10, 10, 10, 11, 13, 13, 13, 16, 18, 22, 22,
		// 23, 24, 24, 27, 27, 28, 28, 28, 99]

		Character arr[] = { 'k', 't', 's', 'a', 'g', 'f', 'q', 'k', 'h', 'i', 'c', 'b' };
		print(introsort.sort(arr));
		// OUTPUT
		// [a, b, c, f, g, h, i, k, k, q, s, t]

	}

}
