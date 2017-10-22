package Sorts;

/**
 * @author dpunosevac
 */
public class ShellSort {

    /**
     * This method implements Generic Shell Sort.
     *
     * @param array The array to be sorted
     */
    public static void shellSort(Comparable[] array) {
        int length = array.length;
        int h = 1;

        while (h < length / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < length; i++) {
                for (int j = i; j >= h && less(array[j], array[j - h]); j -= h) {
                    swap(array, j, j - h);
                }
            }
            h /= 3;
        }
    }

    /**
     * Helper method for exchanging places in array
     *
     * @param array The array which elements we want to swap
     * @param i     index of the first element
     * @param j     index of the second element
     */
    private static void swap(Comparable[] array, int i, int j) {
        Comparable swap = array[i];
        array[i] = array[j];
        array[j] = swap;
    }

    /**
     * This method checks if first element is less then the other element
     *
     * @param v first element
     * @param w second element
     * @return true if the first element is less then the second element
     */
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        // Integer Input
        Integer[] array = {4, 23, 6, 78, 1, 54, 231, 9, 12};

        shellSort(array);

        for (Integer num : array) {
            System.out.println(num);
        }
    }
}