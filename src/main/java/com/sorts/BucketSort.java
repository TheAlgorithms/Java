package com.sorts;

public class BucketSort {

	/** 
	 * Performs a bucket sort of an array in which all the elements are
	 * distributed in different buckets and then each bucket is then sorted individually.
	 * @param array to be sorted and maxValue in the array
	 * @return sorted array
	 */	
	public int[] sort(int[] array, int maxValue) {
		int[] bucket = new int[maxValue + 1];
		int[] sortedArray = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			bucket[array[i]]++;
		}
		int outPos = 0;
		for (int i = 0; i < bucket.length; i++) {
			for (int j = 0; j < bucket[i]; j++) {
				sortedArray[outPos++] = i;
			}
		}
		return sortedArray;
	}

	/** 
	 * Utility method to find max value from array
	*/
	public int maxValue(int[] array) {
		int max = Integer.MIN_VALUE;
		for (int element : array) {
			if(element > max) max = element;
		}
		return max;
	}
}