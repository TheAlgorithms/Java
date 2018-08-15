package DynamicProgramming;

import java.util.Scanner;

public class MaximumSumSubArrayByRemovingAtMostOneElement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int t = s.nextInt();
		while(t-->0)
		{
			int n = s.nextInt();
			int[] arr = new int[n];
			for(int i=0;i<n;i++)
				arr[i] = s.nextInt();
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
			System.out.println(maxsum);
		}
	}

}
