package Maths;

public class FindMinRecursion {
    public static void main(String[] args) {
        int[] array = {2, 4, 9, 7, 19, 94, 5};
        int low = 0;
        int high = array.length - 1;

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
    public static int min(int[] array, int low, int high) {
        if (low == high) {
            return array[low]; //or array[high]
        }

        int mid = (low + high) >>> 1;

        int leftMin = min(array, low, mid); //get min in [low, mid]
        int rightMin = min(array, mid + 1, high); //get min in [mid+1, high]

        return leftMin <= rightMin ? leftMin : rightMin;
    }
}
