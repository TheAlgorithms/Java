package Others;
import java.util.*;

/*  program to find the sum of contiguous subarray within a one-dimensional array of numbers
  which has the largest sum.

  Test Case :

          Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
          Output: 6
          Explanation: [4,-1,2,1] has the largest sum = 6
          
          https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
          */




public class KadaneAlgorithm {


    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int N=sc.nextInt();
        int []nums=new int[N];
        System.out.println(maxSubArray(nums)); //printing the maximum sum

    }
    static  public int maxSubArray(int[] nums) {


        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;
        for(int i = 0; i < nums.length; i ++) {
            currentSum += nums[i];//Adding elements of Array to currentSum
            maxSum = Math.max(maxSum, currentSum); //checking for maximum Sum
            if(currentSum < 0) { // if it's negative then reset currentSum to zero
                currentSum = 0;
            }
        }
        return maxSum;

    }
}



