//Java code for Trapping Rain Water Problem

import java.util.*;

class TrappingRainWater {

	//This function will return the maximum amount of water that can be trapped in between the blocks.
	static int trappedWater(int arr[], int n)
	{
		/*
		 Since the amount of water trapped will be the minimum of the 2 blocks
	       	 we can start it from the side with the low height and then move towards the higher height.	 
		 */

		// initialize the output which will be returned back to the main function
		int res = 0;

		// take the maximum elements on left and right so that the minimum of those can be found out
		int max_left = 0, max_right = 0;

		// indices to traverse the array and traversing the array in O(n) time complexity
		int low = 0, high = n - 1;

		while (low <= high) {
			if (arr[low] < arr[high]) {
				if (arr[low] > max_left) {
					//updating the max value on the left
					max_left = arr[low];
				}
				else {
					// water will be the max value subtracted by the current value
					res += max_left - arr[low];
				}
				low++;
			}
			else {
				if (arr[high] > max_right) {
					//updating the max value on the right
					max_right = arr[high];
				}
				else {
					// water will be the max value subtracted by the current value
					res += max_right - arr[high];
				}
				high--;
			}
		}
		//Sending the final value to the main function
		return res;
	}

	//Main function which will take the inputs and call the above function 
	public static void main(String[] args)
	{
		//Using Scanner Class to take in user input
		//The function will take in 2 inputs: the length of the array and the height of each block
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Length of the array\n"); 
		int n = sc.nextInt();
		int arr[] = new int[n];		//Declaring an array of size n
		System.out.println("\nEnter the values\n");
	        for(int i = 0;i < n;i++){
			arr[i]=sc.nextInt();
		}
		System.out.println("Maximum water that can be trapped between the blocks is "+trappedWater(arr, n));
	}
}
