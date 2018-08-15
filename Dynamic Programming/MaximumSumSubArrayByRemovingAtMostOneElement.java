import java.util.Scanner;

/**
    Author : MAYUR VAID
    A Dynamic Programming based solution for Maximum Sum Subarray by removing atmost one element problem In Java
**/

/**The idea of the algorithm is to first check the maximum sum without removing any element by using Kadane's algorithm.
   Then, remove every index i for 0<=i<=n and find the maximum sum formed. This can be done by precomputing the maximum
   sum from start to index i and from index i to end and storing it in arrays fwd and bkwd respectively. This can also
   be achieved by Kadane's algorithm. Now compare both he max values found and return the overal maximuum.
**/


public class MaximumSumSubArrayByRemovingAtMostOneElement {

	public static int func(int n, int[] arr)
	{
		//Apply Kadane's Algorithm to find the max sum subarray.
		int maxsum = Integer.MIN_VALUE;
		int sum = 0;
		for(int i=0;i<n;i++)
		{
			sum += arr[i];
			if(maxsum<sum)
				maxsum = sum;
			if(sum<0)
				sum = 0;
		}
		int[] fwd = new int[n+2];
		int[] bkwd = new int[n+2];
		fwd[0] = 0;fwd[n+1] = 0;
		bkwd[0] = 0;bkwd[n+1] = 0;
		//Apply Kadanes's Algorithm to find maximum sum subarray upto ith index.
		//Fill fwd array.
		sum = 0;
		for(int i=0;i<n;i++)
		{
			sum += arr[i];
			fwd[i+1] = sum;
			if(sum<0)
				sum = 0;
		}
		//Fill bkwd array.
		sum = 0;
		for(int i=n-1;i>=0;i--)
		{
			sum += arr[i];
			bkwd[i+1] = sum;
			if(sum<0)
				sum = 0;
		}
		//Find sum after removing each index. Update maxsum if required.
		for(int i=1;i<=n;i++)
		{
			sum = fwd[i-1]+bkwd[i+1];
			if(maxsum<sum)
				maxsum = sum;
		}
		return maxsum;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int n = 5;
		int[] arr = {2, 1, 1, -3, 5};
		int maxsum = func(n,arr);
		System.out.println(maxsum);
		}
	}

}
