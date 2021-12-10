package com.thealgorithms.others;

import static org.junit.Assert.*;
import org.junit.Test;

/*
 * A left rotation operation on an array
 * shifts each of the array's elements
 * given integer n unit to the left.
 * 
 * @author sangin-lee 
 */

public class ArrayLeftRotation {

	@Test
	public void testForOneElement() {
		int[] arr = {3};
		int[] result = rotateLeft(arr, 3);
		assertArrayEquals(arr, result);
	}
	
	@Test
	public void testForZeroStep() {
		int[] arr = {3, 1, 5, 8, 6};
		int[] result = rotateLeft(arr, 0);
		assertArrayEquals(arr, result);
	}
	
	@Test
	public void testForEqualSizeStep() {
		int[] arr = {3, 1, 5, 8, 6};
		int[] result = rotateLeft(arr, 5);
		assertArrayEquals(arr, result);
	}
	
	@Test
	public void testForLowerSizeStep() {
		int[] arr = {3, 1, 5, 8, 6};
		int n = 2;
		int[] expected = {5, 8, 6, 3, 1};
		int[] result = rotateLeft(arr, n);
		assertArrayEquals(expected, result);
	}
	
	@Test
	public void testForHigherSizeStep() {
		int[] arr = {3, 1, 5, 8, 6};
		int n = 7;
		int[] expected = {5, 8, 6, 3, 1};
		int[] result = rotateLeft(arr, n);
		assertArrayEquals(expected, result);
	}
	
	/*
	 * Returns the result of left rotation of given array arr and integer n
	 * 
	 * @param arr : int[] given array
	 * 
	 * @param n : int given integer
	 * 
	 * @return : int[] result of left rotation
	 */
	public static int[] rotateLeft(int[] arr, int n) {
		int size = arr.length;
		int[] dst = new int[size];
		n = n % size;
		for(int i = 0; i < size; i++) {
			dst[i] = arr[n];
			n = (n + 1) % size;
		}
		return dst;
	}

}
