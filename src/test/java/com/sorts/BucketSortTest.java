package com.sorts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BucketSortTest {
	@Test
	public static void runTests() {
		BucketSort bucketSort = new BucketSort();
		// Unsorted integer array
        int[] unsorted = new int[]{1, 4, 1, 2, 7, 5, 2};

        // Sorted integer array
        int[] sorted = new int[]{1, 1, 2, 2, 4, 5, 7};

		int maxValue = bucketSort.maxValue(unsorted);

        // Comparing the two integer arrays
        Assertions.assertArrayEquals(sorted, bucketSort.sort(unsorted, maxValue));
	}

}