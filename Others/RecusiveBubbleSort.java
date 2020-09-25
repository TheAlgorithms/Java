package Others;

import java.util.Arrays;

public class RecusiveBubbleSort {
	    // A function to implement bubble sort 
	    static void bubbleSort(int arr[], int n) 
	    { 
	        // Base case 
	        if (n == 1) 
	            return; 
	       
	        // One pass of bubble sort. After 
	        // this pass, the largest element 
	        // is moved (or bubbled) to end. 
	        for (int i=0; i<n-1; i++) 
	            if (arr[i] > arr[i+1]) 
	            { 
	                // swap arr[i], arr[i+1] 
	                int temp = arr[i]; 
	                arr[i] = arr[i+1]; 
	                arr[i+1] = temp; 
	            } 
	       
	        // Largest element is fixed, 
	        // recur for remaining array 
	        bubbleSort(arr, n-1); 
	    } 
	      
	    // Driver Method 
	    public static void main(String[] args) 
	    { 
	        int arr[] = {64, 34, 25, 12, 22, 11, 90}; 
	       
	        bubbleSort(arr, arr.length); 
	          
	        System.out.println("Sorted array : "); 
	        System.out.println(Arrays.toString(arr)); 
	    } 
} 

