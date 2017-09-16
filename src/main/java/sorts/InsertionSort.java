package sorts;

/**
 * @author Varun Upadhyay (https://github.com/varunu28)
 */
public class InsertionSort {

    /**
     * This method implements the Generic Insertion Sort
     *
     * @param array The array to be sorted
     * @param last  The count of total number of elements in array
     *              sorts the array in increasing order
     **/
    public static <T extends Comparable<T>> void sort(T array[], int last) {
        T key;
        for (int j = 1; j < last; j++) {

            // Picking up the key(Card)
            key = array[j];
            int i = j - 1;
            while (i >= 0 && key.compareTo(array[i]) < 0) {
                array[i + 1] = array[i];
                i--;
            }
            // Placing the key (Card) at its correct position in the sorted subarray
            array[i + 1] = key;
        }
    }
}
