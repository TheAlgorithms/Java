/*
    **Problem Statement:**
    Maximum subarray problem can be solved in Linear order of time and space. I would like to add that.

    Basically when we calculate max sum of an subarray we check which is greater ar[i..j] or a[j]. This can be solved in linear time.

    **Example 1:**
    Input: array = [30,-40, 20, 40]
    Output: 60


    **Steps:**
    Kadane's Algorithm
    1. Find the maximum value between the current element and the addition of the current element to the previous sum.
    2. If the current is bigger then we redo. this will also provide the new value to the sum so that the algorithm can continue.
    3. If the max between the two is the value of the addition of the previous sum and the current element which means this value will be the new sum.
    4. When these three steps are done we continue to maintain the max value.

 */
public class MaximumSubarray{

    public static void main(String[] args) {

        int[] arr = {30, -40, 20, 40};

        int sum = findMaxSum(arr);
        System.out.println("The maximum sum in the sub array is : " + sum);
    }

    private static int findMaxSum(int[] arr) {
        int sum = 0;
        int max = 0;

        for (int i = 0; i < arr.length; i++) {
            sum = Math.max(sum + arr[i], arr[i]);
            max = Math.max(sum, max);
        }
        return max;
    }
}
