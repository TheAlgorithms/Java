package Sorts;

/**
 * @author Varun Upadhyay (https://github.com/varunu28)
 */

class InsertionSort {

    /**
     * This method implements the Generic Insertion Sort. Sorts the array in increasing order
     *
     * @param array The array to be sorted
     * @param last  The count of total number of elements in array
     **/

    public static <T extends Comparable<T>> void insertionSort(T array[], int last) {
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

    // Driver Program
    public static void main(String[] args) {
        // Integer Input
        Integer[] array = {4, 23, 6, 78, 1, 54, 231, 9, 12};

        insertionSort(array, array.length);

        // Output => 1 4 6 9 12 23 54 78 231
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();

        // String Input
        String[] array1 = {"c", "a", "e", "b", "d"};

        insertionSort(array1, array1.length);

        //Output => a b c d e
        for (String str : array1) {
            System.out.print(str + "\t");
        }
    }
}
