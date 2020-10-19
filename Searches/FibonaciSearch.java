// Java program for Fibonacci Search 


import java.util.*; 

class Fibonacci 
{ 
	
	public static int min(int x, int y) 
	{ return (x <= y)? x : y; } 

	
	public static int fibMonaccianSearch(int arr[], 
										int x, int n) 
	{ 
		
		int fibMMm2 = 0; // (m-2)'th Fibonacci No. 
		int fibMMm1 = 1; // (m-1)'th Fibonacci No. 
		int fibM = fibMMm2 + fibMMm1; // m'th Fibonacci 

		
		while (fibM < n) 
		{ 
			fibMMm2 = fibMMm1; 
			fibMMm1 = fibM; 
			fibM = fibMMm2 + fibMMm1; 
		} 

		
		int offset = -1; 

		
		while (fibM > 1) 
		{ 
			
			int i = min(offset+fibMMm2, n-1); 

			
			if (arr[i] < x) 
			{ 
				fibM = fibMMm1; 
				fibMMm1 = fibMMm2; 
				fibMMm2 = fibM - fibMMm1; 
				offset = i; 
			} 

			
			fibMm2, cut the subarray after i+1 */
			else if (arr[i] > x) 
			{ 
				fibM = fibMMm2; 
				fibMMm1 = fibMMm1 - fibMMm2; 
				fibMMm2 = fibM - fibMMm1; 
			} 

			/* element found. return index */
			else return i; 
		} 

		
		if(fibMMm1 == 1 && arr[offset+1] == x) 
			return offset+1; 

		/*element not found. return -1 */
		return -1; 
	} 
	
	
	public static void main(String[] args) 
	{ 
		int arr[] = {10, 22, 35, 40, 45, 50, 
					80, 82, 85, 90, 100}; 
		int n = 11; 
		int x = 85; 
		System.out.print ("Found at index: "+ 
				fibMonaccianSearch(arr, x, n)); 
	} 
} 

