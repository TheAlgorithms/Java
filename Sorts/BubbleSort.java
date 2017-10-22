package Sorts;

/**
 * @author Varun Upadhyay (https://github.com/varunu28)
 */

class BubbleSort {
    /**
     * This method implements the Generic Bubble Sort. Sorts the array in increasing order
     *
     * @param array The array to be sorted
     * @param last  The count of total number of elements in array
     **/

    public static <T extends Comparable<T>> void bubbleSort(T array[], int last) {
        //Sorting
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

    // Driver Program
    public static void main(String[] args) {
        // Integer Input
        Integer[] array = {4, 23, 6, 78, 1, 54, 231, 9, 12};

        bubbleSort(array, array.length);

        // Output => 1 4 6 9 12 23 54 78 231
        for (int num : array) {
            System.out.print(num + "\t");
        }
        System.out.println();

        // String Input
        String[] array1 = {"c", "a", "e", "b", "d"};

        bubbleSort(array1, array1.length);

        //Output => a b c d e
        for (String str : array1) {
            System.out.print(str + "\t");
        }
    }
}
