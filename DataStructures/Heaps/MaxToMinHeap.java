// This code is contributed by Tejas Magade
import java.util.Arrays;

class MinHeap
{
	// return left child of `A[i]`
	private static int LEFT(int i) {
		return (2*i + 1);
	}

	// return right child of `A[i]`
	private static int RIGHT(int i) {
		return (2*i + 2);
	}

	// Recursive function to implement the heapify-down algorithm.
	// The node at index `i` and its two direct children
	// violates the heap property
	private static void heapify(int[] A, int i, int size)
	{
		// get left and right child of node at index `i`
		int left = LEFT(i);
		int right = RIGHT(i);

		int smallest = i;

		// compare `A[i]` with its left and right child
		// and find the smallest value
		if (left < size && A[left] < A[i]) {
			smallest = left;
		}

		if (right < size && A[right] < A[smallest]) {
			smallest = right;
		}

		// swap with a child having lesser value and
		// call heapify-down on the child
		if (smallest != i)
		{
			swap(A, i, smallest);
			heapify(A, smallest, size);
		}
	}

	// Utility function to swap two indices in the array
	private static void swap(int[] A, int i, int j)
	{
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}

	// Function to convert a max-heap into a min-heap
	public static void convert(int[] A)
	{
		// Build-Heap: Call heapify starting from the last internal
		// node all the way up to the root node
		int i = (A.length - 2) / 2;
		while (i >= 0) {
			heapify(A, i--, A.length);
		}
	}
}

// Convert max-heap into min-heap in linear time
class Main
{
	public static void main(String[] args)
	{
		/*
					9

			   4         7

			1    -2    6    5
		*/

		// an array representing the max-heap
		int[] A = { 9, 4, 7, 1, -2, 6, 5 };

		// build a min-heap by initializing it by the given array
		MinHeap.convert(A);

		// print the min-heap
		/*
				   -2

			   1        5

			9    4    6    7
		*/

		System.out.println(Arrays.toString(A));
	}
}
