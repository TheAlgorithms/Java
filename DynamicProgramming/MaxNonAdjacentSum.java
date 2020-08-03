package DynamicProgramming;

import java.lang.*;

/**
 * @author Nikolaos Tzamos
 */
public class MaxNonAdjacentSum {
    public static void main(String[] args){
        int array1[] = {1,2,3};
        int array2[] = {1, 5, 3, 7, 2, 2, 6};
        int array3[] = {-1, -5, -3, -7, -2, -2, -6};
        int array4[] = {499, 500, -3, -7, -2, -2, -6};

        System.out.println(maximumNonAdjacentSum(array1));
        System.out.println(maximumNonAdjacentSum(array2));
        System.out.println(maximumNonAdjacentSum(array3));
        System.out.println(maximumNonAdjacentSum(array4));
    }

    /**
     * This method finds the maximum non-adjacent sum of the integers in the nums input array
     * @param nums input array
     */
    static int maximumNonAdjacentSum(int[] nums){
        if (nums.length < 0)
            return 0;

        int maxIncluding = nums[0];
        int maxExcluding = 0;

        for (int i=1; i<nums.length; i++){
            int temp = maxIncluding;
            maxIncluding = maxExcluding + nums[i];
            maxExcluding = Math.max(temp, maxExcluding);
        }

        return Math.max(maxExcluding, maxIncluding);
    }
}
