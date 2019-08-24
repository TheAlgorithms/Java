package com.search;

public class InterpolationSearch {

	/**
	 * A linear interpolation search algorithm that finds the position of a
	 * target value in an sorted array using its lowest value and highest value.
	 * 
	 * Average time-complexity O(log log n) - uniformly distributed.
	 * Worst-case time-complexity O(n) - non-uniformly distributed
	 * Worst-case space complexity O(1)
	 * 
	 * @param <T>This is any comparable type
	 * @param arr This is the array where the element should be found
	 * @param key This is the element to find in the array
	 * @return The index of the key in the array
	 */
	public <T extends Comparable<T>> int findIndex(T arr[], T key) {
		return checkCondition(arr, key, 0, arr.length - 1);
	}

	/**
	 * @param lowIndex The first and smallest element in the sorted array
	 * @param highIndex The last and largest element in the sorted array
	 * @return The found key's index in the array through iteration
	 */
	private <T extends Comparable<T>> int checkCondition(T arr[], T key, int lowIndex, int highIndex) {
		boolean conditionOne = lowIndex <= highIndex && key.compareTo(arr[lowIndex]) >= 0;
		boolean conditionTwo = key.compareTo(arr[lowIndex]) == 0 && key.compareTo(arr[highIndex]) <= 0;
		while (conditionOne || conditionTwo) {
			int position = getPostion(arr, key, lowIndex, highIndex);
			if (arr[position].equals(key))
				return position;

			if (arr[position].compareTo(key) < 0)
				lowIndex = position + 1;
			else
				highIndex = position - 1;
		}
		return -1;
	}

	/**
	 * @return The array's current retrieved index position
	 */
	private <T> int getPostion(T arr[], T key, int lowIndex, int highIndex) {
		String startValueString = arr[lowIndex].toString(); //First convert <T> array element to String
		int startValueInt = Integer.parseInt(startValueString); //Convert String to int to computate later
		String endValueString = arr[highIndex].toString();
		int endValueInt = Integer.parseInt(endValueString);
		String keyValueString = key.toString(); //Repeat for <T> key to later computate
		int keyValueInt = Integer.parseInt(keyValueString);

		int arrayIndexRangeDiff =  highIndex - lowIndex;
		int arrayHighLowValuesDiff =  endValueInt - startValueInt;
		int keyMinusArrayLowValue =  keyValueInt - startValueInt;
		int position = lowIndex + (((arrayIndexRangeDiff) / (arrayHighLowValuesDiff) * (keyMinusArrayLowValue)));
		return position;
	}
}