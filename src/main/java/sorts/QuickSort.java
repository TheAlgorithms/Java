package sorts;

/**
 *
 * @author Varun Upadhyay (https://github.com/varunu28)
 *
 */
public class QuickSort {

    /**
     * This method implements the Generic Quick Sort
     *
     * @param array The array to be sorted
     * @param start The first index of an array
     * @param end The last index of an array
     * sorts the array in increasing order
     **/
    public static <T extends Comparable<T>> void sort(T array[], int start, int end) {
        if (start < end) {
            int PIndex = partition(array, start, end);
            sort(array, start, PIndex - 1);
            sort(array, PIndex + 1, end);
        }
    }

    /**
     * This method finds the partition index for an array
     *
     * @param array The array to be sorted
     * @param start The first index of an array
     * @param end The last index of an array
     * Finds the partition index of an array
     **/
    public static <T extends Comparable<T>> int partition(T array[], int start, int end) {
        T pivot = array[end];
        int PIndex = start;
        for (int i=start;i<end;i++) {
            if (array[i].compareTo(pivot) <= 0) {
                swap(array, i, PIndex);
                PIndex++;
            }
        }
        swap(array, PIndex, end);
        return PIndex;
    }

    /**
     * This method swaps two elements of an array
     *
     * @param array The array to be sorted
     * @param initial The first element
     * @param fin The second element
     * Swaps initial and fin element
     **/
    public static <T extends Comparable<T>> void swap(T[] array, int initial, int fin) {
        T temp = array[initial];
        array[initial] = array[fin];
        array[fin] = temp;
    }
}

