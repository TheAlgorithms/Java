package Maths;

import java.util.ArrayList;

public class FindMaxRecursion {
    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<Integer>();
        array.add(2);
  	array.add(4);
	array.add(9);
	array.add(7);
	array.add(19);
	array.add(94);
	array.add(5);
        int low = 0;
        int high = array.size()- 1;

        System.out.println("max value is " + max(array, low, high));
    }

    /**
     * Get max of array using divide and conquer algorithm
     *
     * @param array contains elements
     * @param low   the index of the first element
     * @param high  the index of the last element
     * @return max of {@code array}
     */
    public static int max(ArrayList<Integer> array, int low, int high) {
        if (low == high) {
            return array.get(low); //or array[high]
        }

        int mid = (low + high)/2;

        int leftMax = max(array, low, mid); //get max in [low, mid]
        int rightMax = max(array, mid + 1, high); //get max in [mid+1, high]

        return leftMax >= rightMax ? leftMax : rightMax;
    }
}
