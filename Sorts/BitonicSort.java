
public class BitonicSort {
	/**
	 * The parameter sortingDirection indicates the sorting direction, ASCENDING or
	 * DESCENDING; if (a[i] > a[j]) agrees with the direction, then a[i] and
	 * a[j] are interchanged.
	 */
	void compareAndSwap(int arr[], int i, int j, int sortingDirection) {
		if ((arr[i] > arr[j] && sortingDirection == 1) || (arr[i] < arr[j] && sortingDirection == 0)) {
			// Swapping elements
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}

	/**
	 * This function is being called to merge two sorted arrays in same order.
	 * 
	 * @param arr - array to be sorted
	 * @param low - starting index position of the sequence to be sorted
	 * @param noOfEleToBeSorted - Number of elements to be sorted
	 * @param sortingDirection - 0 -> ascending direction, 1 -> descending direction
	 */
	void bitonicMerge(int arr[], int low, int noOfEleToBeSorted, int sortingDirection) {
		if (noOfEleToBeSorted > 1) {
			int k = noOfEleToBeSorted / 2;
			for (int i = low; i < low + k; i++)
				compareAndSwap(arr, i, i + k, sortingDirection);
			bitonicMerge(arr, low, k, sortingDirection);
			bitonicMerge(arr, low + k, k, sortingDirection);
		}
	}
	
	/**
	 * This function first produces a bitonic sequence by recursively sorting its
	 * two halves in opposite sorting orders, and then calls bitonicMerge to
	 * make them in the same order
	 * 
	 * @param arr - array to be sorted
	 * @param low - starting index position of the sequence to be sorted
	 * @param noOfEleToBeSorted - Number of elements to be sorted
	 * @param sortingDirection - 0 -> ascending direction, 1 -> descending direction
	 */
	void bitonicSort(int arr[], int low, int noOfEleToBeSorted, int sortingDirection) {
		if (noOfEleToBeSorted > 1) {
			int k = noOfEleToBeSorted / 2;

			// sort in ascending order since sortingDirection here is 1
			bitonicSort(arr, low, k, 1);

			// sort in descending order since sortingDirection here is 0
			bitonicSort(arr, low + k, k, 0);

			// Will merge whole sequence in ascending order
			// since sortingDirection=1.
			bitonicMerge(arr, low, noOfEleToBeSorted, sortingDirection);
		}
	}

	/**
	 * Caller of bitonicSort for sorting the entire array of length N in
	 * ASCENDING order
	 */
	void sort(int arr[], int N, int up) {
		bitonicSort(arr, 0, N, up);
	}

	/* A utility function to print array of size n */
	static void printArray(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	// Driver method
	public static void main(String args[]) {
		int arr[] = { 3, 7, 4, 8, 6, 2, 1, 5 };
		int up = 1;
		BitonicSort ob = new BitonicSort();
		ob.sort(arr, arr.length, up);
		System.out.println("\nSorted array");
		printArray(arr);
	}
}