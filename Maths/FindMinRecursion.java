package Maths;

public class FindMinRecursion {
    public static void main(String[] args) {
        int[] array = {2, 4, 9, -7, 19, 94, 5};
        int low = 0;
        int high = array.length - 1;

        assert min(array, low, high) == -7;
        assert min(array, array.length) == -7;
    }

    /**
     * Get min of array using divide and conquer algorithm
     *
     * @param array contains elements
     * @param low   the index of the first element
     * @param high  the index of the last element
     * @return min of {@code array}
     */
    public static int min(int[] array, int low, int high) {
        if (low == high) {
            return array[low]; //or array[high]
        }

        int mid = (low + high) >>> 1;

        int leftMin = min(array, low, mid); //get min in [low, mid]
        int rightMin = min(array, mid + 1, high); //get min in [mid+1, high]

        return Math.min(leftMin, rightMin);
    }

    /**
     * Get min of array using recursion algorithm
     *
     * @param array contains elements
     * @param len   length of given array
     * @return min value of {@code array}
     */
    public static int min(int[] array, int len) {
        return len == 1 ? array[0] : Math.min(min(array, len - 1), array[len - 1]);
    }
}
