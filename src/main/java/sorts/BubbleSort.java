package sorts;

/**
 * @author Varun Upadhyay (https://github.com/varunu28)
 */

public class BubbleSort {

    /**
     * This method implements the Generic Bubble Sort
     *
     * @param array The array to be sorted
     * @param last  The count of total number of elements in array
     *              sorts the array in increasing order
     **/
    public static <T extends Comparable<T>> void sort(T array[], int last) {
        boolean swap;
        do {
            swap = false;
            for (int count = 0; count < last - 1; count++) {
                int comp = array[count].compareTo(array[count + 1]);
                if (comp > 0) {
                    T temp = array[count];
                    array[count] = array[count + 1];
                    array[count + 1] = temp;
                    swap = true;
                }
            }
            last--;
        } while (swap);
    }
}
