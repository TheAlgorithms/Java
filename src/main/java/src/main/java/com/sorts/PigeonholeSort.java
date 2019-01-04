package src.main.java.com.sorts;

public class PigeonholeSort {

    /**
     * This method sorts the array using Pigeonhole sort technique.
     * <p>
     * Pigeonhole sorting is a sorting algorithms that is suitable for sorting lists of elements where the number
     * of elements and the number of possible key values are approximately the same.
     * <p>
     * It requires O(n + Range) time where n is number of elements in input array and ‘Range’ is number of possible
     * values in array.
     *
     * @param arr The array to be sorted
     * @return arr Sorted array
     */
    public Integer[] sort(Integer[] arr) {

        // Find maximum and minimum elements in array
        int min = arr[0];
        int max = arr[0];

        for (Integer integer : arr) {

            // For minimum value
            if (min > integer) {
                min = integer;
            }

            // For maximum value
            if (max < integer) {
                max = integer;
            }
        }

        // Range
        int range = max - min + 1;

        // Pigeonhole array
        int[] pigeonholes = new int[range];

        // Put each element of arr in its pigeonhole
        for (Integer integer : arr) {
            pigeonholes[integer - min] = integer;
        }

        // Index for the arr
        int index = 0;

        // Loop over pigeonhole array
        for (int pigeonhole : pigeonholes) {

            // Put non zero elements from the pigeonhole array to the current element of arr
            if (pigeonhole != 0) {
                arr[index++] = pigeonhole;
            }
        }

        return arr;
    }
}
