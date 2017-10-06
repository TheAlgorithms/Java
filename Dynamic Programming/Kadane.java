package kadane;
import java.util.*;
import java.io.*;
/*Find the sum of contiguous subarray within a one-dimensional array
of numbers which has the largest sum.*/
/*In this case the maximum sum will come from segment 
[4,3,-1,5]*/
public class kadane {
	public static void main(String args[])
	{
		int [] testArray={-2,4,3,-1,5,-6,-2};
		System.out.println("Maximum continuous sum is "+ kadane(testArray));
	}
	static int kadane(int array[])
	{
		int size=array.length;
		int maxSoFar=array[0];
		int curMax=array[0];
		for(int i=1;i<size;i++)
		{			
			if(curMax+array[i]>array[i])
				curMax=curMax+array[i];
			else
				curMax=array[i];
			if(maxSoFar<curMax)
				maxSoFar=curMax;			
		}
		return maxSoFar;
	}
	

}
