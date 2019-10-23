package DynamicProgramming;

import java.util.Arrays;

public class MaximumSubArrayDP {

    //bottom up approach
    public int solve(int [] a){
        int [] solution = new int[a.length+1];
        solution[0] = 0;

        for (int j = 1; j <solution.length ; j++) {
            solution[j] = Math.max(solution[j-1]+a[j-1],a[j-1]);
        }
        //this will print the solution matrix
        //System.out.println(Arrays.toString(solution));
        //now return the maximum in the solution arrayyy
        int result = solution[0];
        for (int j = 1; j <solution.length ; j++) {
            if(result<solution[j])
                result = solution[j];
        }

        return result;
    }

    public static void main(String[] args) {
        int arrA[] = { 1, 2, -3, -4, 2, 7, -2, 3 };
        MaximumSubArrayDP i = new MaximumSubArrayDP();
        System.out.println("Maximum subarray is  " + i.solve(arrA));
    }
}
