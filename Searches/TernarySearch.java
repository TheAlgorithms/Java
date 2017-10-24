
/**
 * Ternary search is a divide-and-conquer algorithm to determine the position of
 * a specific value in a sorted array
 *
 */

public class TernarySearch {
	/** call function **/
	public static int ternarySearch(int[] A, int value) {
		return ternarySearch(A, value, 0, A.length - 1);
	}

	/** TernarySearch function **/
	public static int ternarySearch(int[] A, int value, int start, int end) {
		if (start > end)
			return -1;

		/** First boundary: add 1/3 of length to start **/
		int mid1 = start + (end - start) / 3;
		/** Second boundary: add 2/3 of length to start **/
		int mid2 = start + 2 * (end - start) / 3;

		if (A[mid1] == value)
			return mid1;
		else if (A[mid2] == value)
			return mid2;
		/** Search 1st third **/
		else if (value < A[mid1])
			return ternarySearch(A, value, start, mid1 - 1);
		/** Search 3rd third **/
		else if (value > A[mid2])
			return ternarySearch(A, value, mid2 + 1, end);
		/** Search middle third **/
		else
			return ternarySearch(A, value, mid1, mid2);
	}

	/** Main method **/
	public static void main(String[] args) {
		int arr[] = { 2, 5, 15, 24, 31, 47, 59, 61, 79, 97 };
		int elementToBeSearched = 24;

		int result = ternarySearch(arr, elementToBeSearched);

		if (result == -1)
			System.out.println("\n" + elementToBeSearched + " element not found");
		else
			System.out.println("\n" + elementToBeSearched + " element found at position " + result);

	}
}
