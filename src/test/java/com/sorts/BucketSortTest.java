package com.sorts;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BucketSortTest {
	static Random RANDOM = new Random();

	@Test
	public static void runTests() {
		final int NUM_TESTS = 10;
		BucketSort bucketSort = new BucketSort();
		for (int i = 1; i <= NUM_TESTS; i++) {
			Integer[] array = new Integer[i];
			for (int j = 0; j < i; j++) {
				array[j] = randInt(-1000000, +1000000);
			}
			int maxValue = bucketSort.maxValue(array);
			array = bucketSort.sort(array, maxValue);
			Integer[] arrayCopy = array.clone();
			Arrays.sort(arrayCopy);
			Assertions.assertArrayEquals(arrayCopy, array);
		}
	}

	static int randInt(int min, int max) {
		return RANDOM.nextInt((max - min) + 1) + min;
	}

}