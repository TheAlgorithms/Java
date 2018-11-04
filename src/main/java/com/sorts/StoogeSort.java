package src.main.java.com.sorts;

import static src.main.java.com.sorts.SortUtils.swap;
import static src.main.java.com.sorts.SortUtils.less;

public class StoogeSort {

	/**
	 * This method implements recursion StoogeSort
	 * 
	 * @param int[] array to store number elements
	 * @param f first element in the array
	 * @param l last element in the array
	 */
	public <T extends Comparable<T>> T[] sort(T[] arr, int f, int l) {

		// Ends recursion when met
		if (f >= l)
			return arr;

		if (less(arr[l], arr[f])) {
			swap(arr, f, l);
		}

		if (l - f + 1 > 2) {
			int entry = (l - f + 1) / 3;

			// Does a recursive sort of the first two thirds elements
			sort(arr, f, l - entry);

			// Does a recursive sort of the last two thirds elements
			sort(arr, f + entry, l);

			// Another recursive sort first two thirds elements to confirm
			sort(arr, f, l - entry);
		}
		return arr;
	}
}