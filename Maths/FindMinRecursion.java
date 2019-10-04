package Maths;

import java.util.ArrayList;

public class FindMinRecursion {
    public static void main(String[] args) {
        int[] array = {2, 4, 9, 7, 19, 94, 5};
	ArrayList <Integer> array = new ArrayList<Integer>();
	array.add(2);
	array.add(4);
	array.add(9);
	array.add(7);
	array.add(19);
	array.add(94);
	array.add(5);
        int low = 0;
        int high = array.size() - 1;

        System.out.println("min value is " + min(array, low, high));
    }

    /**
     * Get min of array using divide and conquer algorithm
     *
     * @param array contains elements
     * @param low   the index of the first element
     * @param high  the index of the last element
     * @return min of {@code array}
     */
    public static int min(ArrayList<Integer> array, int low, int high) {
        if (low == high) {
            return array.get(low); //or array[high]
        }

        int mid = (low + high)/2;

        int leftMin = min(array, low, mid); //get min in [low, mid]
        int rightMin = min(array, mid + 1, high); //get min in [mid+1, high]

        return leftMin <= rightMin ? leftMin : rightMin;
    }
}
