package com.search;

public class JumpSearch {

	/**
	 * A jump search algorithm that finds the position of a key by moving over
	 * fixed block ranges in a sorted array, and linear searches back on itself to
	 * find it.
	 * 
	 * Worst case time complexity: O(N^(1/2)) - square root n 
	 * Average case time complexity: O(N^(1/2)) - square root n 
	 * Worst case Space complexity: O(1)
	 * 
	 * @param <T> This is any comparable type
	 * @param arr This is the array where the element should be found
	 * @param key This is the element to find in the array
	 * @return The index of the key in the array
	 */
	public <T extends Comparable<T>> int findIndex(T arr[], T key) {
		return checkCondition(arr, key, arr.length);
	}

	/**
	 * @param arrLength The array's length
	 * @return The index position of the key in the array
	 */
	public <T extends Comparable<T>> int checkCondition(T arr[], T key, int arrLength) {
		int step = (int) Math.floor(Math.sqrt(arrLength)); // Find jump block
		int previous = 0; // Find block where element is / or not present

		// Use ternary operator to find if step or array length is min value
		// and then minus the min value by one
		int minVal = (step < arrLength) ? step - 1 : arrLength - 1;

		String arrayMinValIndexString = arr[minVal].toString();
		int arrayMinValIndexInt = Integer.parseInt(arrayMinValIndexString);
		String keyValueString = key.toString();
		int keyValueInt = Integer.parseInt(keyValueString);

		// Increment next step and previous step in block to find range block
		while (arrayMinValIndexInt < keyValueInt) {
			previous = step;
			step += (int) Math.floor(Math.sqrt(arrLength));
			if (previous >= arrLength)
				return -1;
			minVal = (step < arrLength) ? step - 1 : arrLength - 1;
			arrayMinValIndexString = arr[minVal].toString();
			arrayMinValIndexInt = Integer.parseInt(arrayMinValIndexString);
		}
		// Get key position in linear search
		int position = linearSearchBlock(arr, key, step, previous, keyValueInt, arrLength, minVal);
		return position;
	}

	/**
	 * @param step The next block index in the array
	 * @param previous The previous block index in the array
	 * @param keyValueInt The key in the format of an integer
	 * @param minVal The minimum value of either next step or array length 
	 * @return The index position of the key in the array
	 */
	public <T extends Comparable<T>> int linearSearchBlock(T arr[], T key, int step, int previous, int keyValueInt,
			int arrLength, int minVal) {

		// Linear search starting from previous block forwards.
		String arrayPositionString = arr[previous].toString();
		int arrayPositionValue = Integer.parseInt(arrayPositionString);
		while (arrayPositionValue < keyValueInt) {
			// If in next block or end of array length, key not in array
			if (previous == minVal)
				return -1;
			previous++;
			// Update arrayPositionValue in iteration
			minVal = (step < arrLength) ? step - 1 : arrLength - 1;
			arrayPositionString = arr[previous].toString();
			arrayPositionValue = Integer.parseInt(arrayPositionString);

		}
		// If the key is found
		if (arrayPositionValue == keyValueInt)
			return previous;
		return -1;
	}
}
