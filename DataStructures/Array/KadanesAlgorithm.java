public class KadanesAlgorithm 
{
	    public static void main (String[] args)
	    {
	        int [] arr = {-2, -3, 4, -1, -2, 1, 5, -3};
	        System.out.println("Maximum contiguous array sum is " + maxSubArraySum(arr) + " Kadanes Algorithm");
	    }
	 
	    static int maxSubArraySum(int arr[])
	    {
	        int sum = Integer.MIN_VALUE, max_ending_here = 0;
	 
	        for (int i = 0; i < arr.length; i++)
	        {
	            max_ending_here = max_ending_here + arr[i];
	            if (sum < max_ending_here)
	            {
	            	sum = max_ending_here;
	            }
	            if (max_ending_here < 0)
	            {
	            	max_ending_here = 0;
	            }
	        }
	        return sum;
	    }
}
