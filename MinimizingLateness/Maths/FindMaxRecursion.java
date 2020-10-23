package Maths;

import java.util.Arrays;
import java.util.Random;

public class FindMaxRecursion {
    public static void main(String[] args) {
        Random rand = new Random();

        /* rand size */
        int size = rand.nextInt(100) + 1;
        int[] array = new int[size];

        /* init array with rand numbers */
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt() % 100;
        }

        assert max(array, array.length) == Arrays.stream(array).max().getAsInt();
        assert max(array, 0, array.length - 1) == Arrays.stream(array).max().getAsInt();
    }

    /**
     * Get max of array using divide and conquer algorithm
     *
     * @param array contains elements
     * @param low   the index of the first element
     * @param high  the index of the last element
     * @return max of {@code array}
     */
    public static int max(int[] array, int low, int high) {
        if (low == high) {
            return array[low]; //or array[high]
        }

        int mid = (low + high) >>> 1;

        int leftMax = max(array, low, mid); //get max in [low, mid]
        int rightMax = max(array, mid + 1, high); //get max in [mid+1, high]

        return Math.max(leftMax, rightMax);
    }

    /**
     * Get max of array using recursion algorithm
     *
     * @param array contains elements
     * @param len   length of given array
     * @return max value of {@code array}
     */
    public static int max(int[] array, int len) {
        return len == 1 ? array[0] : Math.max(max(array, len - 1), array[len - 1]);
    }
}
