// Java Program to find the 
// Cartesian Product of Two Sets 
import java.io.*; 
import java.util.*; 

class CartesianProduct { 

	static void findCart(int arr1[], int arr2[], 
									int n, int n1) 
	{ 
		for (int i = 0; i < n; i++) 
		for (int j = 0; j < n1; j++) 
			System.out.print("{"+ arr1[i]+", "
							+ arr2[j]+"}, "); 
	} 
	// Driver code 
	public static void main (String[] args) { 
		
		// first set 
		int arr1[] = { 1, 2, 3 }; 
		
		// second set 
		int arr2[] = { 4, 5, 6 }; 
		
		int n1 = arr1.length; 
		int n2 = arr2.length; 
		findCart(arr1, arr2, n1, n2); 
	} 
}