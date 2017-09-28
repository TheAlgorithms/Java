package sorts;

/**
 * @author Varun Upadhyay (https://github.com/varunu28)
 */
public class SelectionSort {

    /**
     * This method implements the Generic Selection Sort
     *
     * @param arr The array to be sorted
     * @param n   The count of total number of elements in array
     *            sorts the array in increasing order
     **/
    public static <T extends Comparable<T>> void sort(T[] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j].compareTo(arr[min]) < 0) {
                    min = j;
                }
            }
            // Swapping if index of min is changed
            if (min != i) {
                T temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
    }
}
