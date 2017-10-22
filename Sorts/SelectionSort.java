package Sorts;

/**
 * @author Varun Upadhyay (https://github.com/varunu28)
 */

public class SelectionSort {

    /**
     * This method implements the Generic Selection Sort. Sorts the array in increasing order.
     *
     * @param array  The array to be sorted
     * @param length The count of total number of elements in array
     **/

    public static <T extends Comparable<T>> void selectionSort(T[] array, int length) {
        for (int i = 0; i < length - 1; i++) {
            // Initial index of min
            int min = i;

            for (int j = i + 1; j < length; j++) {
                if (array[j].compareTo(array[min]) < 0) {
                    min = j;
                }
            }

            // Swapping if index of min is changed
            if (min != i) {
                T temp = array[i];
                array[i] = array[min];
                array[min] = temp;
            }
        }
    }

    // Driver Program
    public static void main(String[] args) {

        // Integer Input
        Integer[] intArray = {4, 23, 6, 78, 1, 54, 231, 9, 12};
        int length = intArray.length;

        selectionSort(intArray, length);

        // Output => 1  4  6  9  12  23  54  78  231
        for (int num : intArray) {
            System.out.print(num + "\t");
        }

        System.out.println();

        // String Input
        String[] stringArray = {"c", "a", "e", "b", "d"};
        length = stringArray.length;

        selectionSort(stringArray, length);

        // Output => a  b  c  d  e
        for (String str : stringArray) {
            System.out.print(str + "\t");
        }
    }
}
