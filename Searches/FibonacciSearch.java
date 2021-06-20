// Fibonacci Search Algorithm in java

import java.util.*; 
import java.io.*;
class FibonacciSearch 
{ 
	// Static function to find minimum of two elements
	public static int min(int x, int y) { return (x <= y)? x : y; } 

	// If x is present, returns index of x, else returns -1
	public static int fibSearch(int arr[],int x, int n) 
	{ 
		/* Initialize the (m-2)'th , (m-1)'th and m'th fibonacci numbers */
		int fM2 = 0; 					// (m-2)'th Fibonacci No. 
		int fM1 = 1; 					// (m-1)'th Fibonacci No. 
		int fibM = fM2 + fM1; 			// m'th Fibonacci No.

		/* fibM is going to store the smallest 
		Fibonacci Number greater than or equal to n */
		while (fibM < n) 
		{ 
			fM2 = fM1; 
			fM1 = fibM; 
			fibM = fM2 + fM1; 
		} 

		// Marks the eliminated range from front 
		int offset = -1; 

		/* While there are elements to be inspected. 
		Note that we compare arr[fM2] with x. 
		When fibM becomes 1, fM2 becomes 0 */
		while (fibM > 1) 
		{ 
			// Check if fM2 is a valid location 
			int i = min(offset+fM2, n-1); 

			/* If x is greater than the value at 
			index fM2, cut the subarray array 
			from offset to i */
			if (arr[i] < x) 
			{ 
				fibM = fM1; 
				fM1 = fM2; 
				fM2 = fibM - fM1; 
				offset = i; 
			} 

			/* If x is less than the value at index 
			fM2, cut the subarray after i+1 */
			else if (arr[i] > x) 
			{ 
				fibM = fM2; 
				fM1 = fM1 - fM2; 
				fM2 = fibM - fM1; 
			} 

			/* element found. return index */
			else return i; 
		} 

		/* comparing the last element with x */
		if(fM1 == 1 && arr[offset+1] == x) 
			return offset+1; 

		/*element not found. return -1 */
		return -1; 
	} 
	
	// the main function 
	public static void main(String[] args) throws IOException
	{
		/* The main function throws IOException because of the readLine() function of the BufferedReader class.*/
		/* BufferedReader is used to decrease the time for taking input. */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the size of the array");
		int n = Integer.parseInt(br.readLine());	
		//initliaze a array of size n						
		int arr[] = new int[n];												
		System.out.println("Enter the elements of the array in sorted order(ascending)");
		for(int i=0;i<n;i++){
			//taking input of the elements of array one by one
			arr[i] = Integer.parseInt(br.readLine());						
		}
		System.out.println("Enter the key element to search");
		// x is the key element we need to search from the array
		int x = Integer.parseInt(br.readLine());
		int index = fibSearch(arr,x,n);	
		if(index!=-1)						
			System.out.println ("Found at index: "+ 
								index); 
		else
			System.out.println("Element not found");
	} 
} 


/*
Input:

Enter the size of the array
9
Enter the elements of the array in sorted order(ascending)
10
19
20
23
25
40
42
55
60
Enter the key element to search
55

Output:
Found at index: 7

*/


