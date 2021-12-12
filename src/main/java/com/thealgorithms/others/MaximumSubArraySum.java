package others;

public class MaximumSubArraySum {
    //Maximum Subarray Sum using Divide and Conquer algorithm


    public static int MaximumSum(int[] Arr, int firstindex, int lastindex) {

        if (lastindex == firstindex) {//if the first index and the last index of array are same
            return Arr[ firstindex ];//it means array contains only one element
            //and this element equals maximum subarray sum.
        }

        // Finding middle element's index of the array
        int middle = ( firstindex + lastindex ) / 2;

        // Finding maximum subarray sum for the left subarray

        int leftMax = -1000000000;
        int sum = 0;
        for (int i = middle; i >= firstindex; i--) {
            sum += Arr[ i ];//addition for sum
            if (sum > leftMax) {
                leftMax = sum;
            }
        }

        // Find maximum subarray sum for the right subarray
        int rightMax = -1000000000;//should be minimum value
        sum = 0;
        for (int i = middle + 1; i <= lastindex; i++) {
            sum += Arr[ i ];//addition for  sum
            if (sum > rightMax) {
                rightMax = sum;
            }
        }

        // Recursive part
        int maxLeftRight = Math.max (MaximumSum (Arr, firstindex, middle),//left part of the array
                MaximumSum (Arr, middle + 1, lastindex));//right part of the array

        // return maximum of the three
        return Math.max (maxLeftRight, leftMax + rightMax);
    }


}
