package Searches;

import java.util.Scanner;

/**
 * Program			=>		Implementation of Fibonacci Search in Java
 * Documentation	=> 		provided at the end of the Program
 * Issue Details	=> 		https://github.com/TheAlgorithms/Java/issues
 * Contributed by	=>		@sumitaccess007 : https://github.com/sumitaccess007
 */

public class FibonacciSearch
{

	// Main Function - Starting point of any Java Program
	public static void main(String[] args)
	{

		// STEP-1 - Take Input from User - For number of elements
		int n;
		Scanner scanner = new Scanner(System.in);	// Scanner Class is used here to get the user input, it is part of java.util package.
		System.out.println("Enter the Number of Elements : ");
		n = scanner.nextInt();	// this method scans the next token of the input from user as an int data type

		// STEP-2 - Add the number of elements (n) to array of integers
		int inputArray[];	//Array Declaration
		inputArray = new int[n];	// Allocating memory to Array
		System.out.println("Enter those "+ n + " Elements (in Ascending/Descending order) : ");
		for (int i=0; i<n; i++)
		{
			inputArray[i] = scanner.nextInt();
		}

		// STEP-3 - Ask User to Enter the number to find in above array
		int search;
		System.out.println("Enter the number to search : ");
		search = scanner.nextInt();

		// STEP-4 - Call function fibonacciSearch() to find the position of element the array as per Fibonacci Search Logic
		if(fibonacciSearch(inputArray, search) == -1)
		{
			System.out.println("OOPS ... Element "+ search + " is not found.");
		} else {
			System.out.println("Congrats :) ... Element "+ search + " is present at location "+ fibonacciSearch(inputArray, search));
		}
	}


	// fibonacciSearch() function for Fibonacci Search Logic
	public static int fibonacciSearch(int[] array, int search){
		// STEP-1 - Initialize the fibonacci numbers
		int fib_NMinus2 = 0;	// (n-2)th Fibonacci Number
		int fib_NMinus1 = 1;	// (n-1)th Fibonacci Number
		int fib_N = fib_NMinus2 + fib_NMinus1;	// (n)th Fibonacci Number

		// STEP-2 - fib_N is going to store the smallest fibonacci number greater than or equal to n
		// Here we used the concept of Swapping of numbers without using the temp variable
		while(fib_N < array.length)
		{
			fib_NMinus2 = fib_NMinus1;
			fib_NMinus1 = fib_N;
			fib_N = fib_NMinus2 + fib_NMinus1;
		}

		// STEP-3 - Variable to eliminate the range of variables from the front of the array
		int offset = -1;

		// STEP-4 - 
		while(fib_N > 1)
		{
			int i = minimum(offset + fib_NMinus2, array.length-1);

			if(array[i-1] < search)
			{
				fib_N = fib_NMinus1;
				fib_NMinus1 = fib_NMinus2;
				fib_NMinus2 = fib_N - fib_NMinus1;
				offset = i;
			} else if (array[i-1] > search)
			{
				fib_N = fib_NMinus2;
				fib_NMinus1 = fib_NMinus1 - fib_NMinus2;
				fib_NMinus2 = fib_N - fib_NMinus1;
			} else
			{
				return i;	// Element found, so return the index of that element
			}
		}

		if (fib_NMinus1 == 1 && array[offset] == search)
		{
			return offset+1;
		}
		return -1;
	}

	// Utility function to find the minimum of two elements
	public static int minimum(int x, int y)
	{
		return ((x <=y) ? x : y);
	}

}

/*	***** DOCUMENTATION *****
	About Fibonacci Search -
			=> Fibonacci Search is applicable on Sorted Arrays (Like Binary Search).
			=> It is a comparison based technique that uses Fibonacci numbers to search an element.

	Which Algorithm Fibonacci Search Uses ?
			=> Divide and Conquer Algorithm

	ALGORITHM (Logic) Explanation -
			=> STEP-1 - Find the smallest Fibonacci number greater than or equal to n (based on this initialize fib_N, fib_NMinus1, fib_NMinus2)
			=> STEP-2 - While the array has the elements to be checked
						Compare x (number to search) with the last element of the range covered by fib_NMinus2
							If x is equal to the element (If x matches)
								return index value
							Else If x is less than the element
								move the three Fibonacci variables two Fibonacci down,
								indicating elimination of approximately rear two-third of the remaining array.
							Else If x is greater than the element
								move the three Fibonacci variables one Fibonacci down,
								indicating elimination of approximately front one-third of the remaining array.
								Also reset offset to index
						Since there might be a single element remaining for comparison,
						check if fib_NMinus1 is 1. If Yes, compare x with that remaining element.
							If match
								return index.


	Algorithm Explanation with Example -
			Input Array = [5,10,15,20,25,30]
			n = 6
			smallest Fibonacci number greater than or equal to n = 8

	What is the Complexity of Fibonacci Search ?
			=> O(log(n)) (same as Binary Search)

	Fibonacci Search VS Binary Search 
			=> Similarities -
				1. Both works on Sorted Arrays
				2. Both uses Divide and Conquer Algorithm
				3. Both have O(log(n)) time complexity

			=> Differences -
				1. Fibonacci Search divides array in unequal parts, while Binary Search divides array in equal parts.
				2. Fibonacci Search uses "+" and "-" operator to divide the range, while Binary Search uses "/" (division) operator to divide the range.

	When to Use Fibonacci Search and Benefits of using Fibonacci Search over Binary Search ?
				1. "/" (division) operator is more costly in some CPUs so we can go for Fibonacci Search.
				2. When Input Array size is big (which cannot fit in CPU cache or even in RAM), then Fibonacci Search can be more useful than Binary Search.



 ***** Program Usage *****
	Input - 
				1. sorted array of size n
				2. element x to be searched in sorted array

	Variables Meanings -
				1. n - Size of the array (number of elements to add in array)
				2. inputArray - Array containing the elements
				3. search - Element to search in the array

	Output -
				Return the index of x if it is present in the array,
				else return -1

	Time Complexity - O(log(n))


 ***** Sample IO *****
	Input -
			Array = {8,12,15,50,80,85,90,100}
			Element to search = 15
	Output - 3

 */
