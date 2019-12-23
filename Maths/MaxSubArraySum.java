package Maths;


/**
 * Class for searching all the positive contiguous segments of the array and keep track of maximum
 * sum contiguous segment among all positive segments. Each time we get a positive sum compare it with 
 * the last maximum sum and update it if it is greater.
 * 
 * @author Mohamed EL Amri
 * @version 2019.12.23
 *
 */
public class MaxSubArraySum {

	public static void main(String[] args) {
		
		int [] a = {-2, -3, 4, -1, -2, 1, 5, -3};
		System.out.println("Maximum contiguous sum is : " + maxSubArraySum(a));
	}
	
	/**
     * Method that search for the maximum contiguous segments of the array a[]
     *
     * @param a[] The table that contains a list of numbers, numbers can be negative.
     * @return max_so_far that contains the maximum contiguous sum
     */
	static int maxSubArraySum(int a[]) 
    { 
        int size = a.length; 
        int max_so_far = Integer.MIN_VALUE, max_ending_here = 0; 
  
        for (int i = 0; i < size; i++) 
        { 
            max_ending_here = max_ending_here + a[i]; 
            if (max_so_far < max_ending_here) 
                max_so_far = max_ending_here; 
            if (max_ending_here < 0) 
                max_ending_here = 0; 
        } 
        return max_so_far; 
    }
    
}
