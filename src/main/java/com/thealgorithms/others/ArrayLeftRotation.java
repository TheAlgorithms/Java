package com.thealgorithms.others;

import java.util.Scanner;

/*
 * A left rotation operation on an array
 * shifts each of the array's elements
 * given integer n unit to the left.
 * 
 * @author sangin-lee 
 */

public class ArrayLeftRotation {

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
		for (int i = 0; i < size; i++) {
			dst[i] = arr[n];
			n = (n + 1) % size;
		}
		return dst;
	}

	public static void printIntegerArray(int[] arr) {
		int size = arr.length;
		String str = "[ ";
		for (int i = 0; i < size; i++) {
			str += arr[i] + " ";
		}
		str += "]";
		System.out.println(str);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the size of array: ");
		int size = scanner.nextInt();
		int[] arr = new int[size];

		System.out.println("Enter the integer values for the array, " + "use spaces to separate values entered: ");
		for (int i = 0; i < size; i++) {
			arr[i] = Integer.parseInt(scanner.next());
		}

		System.out.println("Enter how many steps you want to rotate: ");
		int n = scanner.nextInt();

		System.out.println("Original array: ");
		printIntegerArray(arr);

		System.out.println("Result of left rotation by " + n + ": ");
		printIntegerArray(rotateLeft(arr, n));
		
		scanner.close();
	}
}