package Sorts;

/**
 * @author Varun Upadhyay (https://github.com/varunu28)
 */

class QuickSort {

    /**
     * This method implements the Generic Quick Sort. Sorts the array in increasing order.
     *
     * @param array The array to be sorted
     * @param start The first index of an array
     * @param end   The last index of an array
     **/
    public static <T extends Comparable<T>> void quickSort(T array[], int start, int end) {
        if (start < end) {
            int partitionIndex = partition(array, start, end);
            quickSort(array, start, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, end);
        }
    }

    /**
     * This method finds the partition index for an array
     *
     * @param array The array to be sorted
     * @param start The first index of an array
     * @param end   The last index of an array
     **/

    public static <T extends Comparable<T>> int partition(T array[], int start, int end) {
        T pivot = array[end];
        int partitionIndex = start;
        for (int i = start; i < end; i++) {
            if (array[i].compareTo(pivot) <= 0) {
                swap(array, i, partitionIndex);
                partitionIndex++;
            }
        }
        swap(array, partitionIndex, end);
        return partitionIndex;
    }

    /**
     * This method swaps two elements of an array
     *
     * @param array   The array to be sorted
     * @param initial The first element
     * @param fin     The second element
     **/

    public static <T extends Comparable<T>> void swap(T[] array, int initial, int fin) {
        T temp = array[initial];
        array[initial] = array[fin];
        array[fin] = temp;
    }

    // Driver Program
    public static void main(String[] args) {

        // For integer input
        Integer[] array = {3, 4, 1, 32, 0, 2, 44, 111, 5};

        quickSort(array, 0, array.length - 1);

        //Output => 0 1 2 3 4 5 32 44 111
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();

        // String Input
        String[] stringArray = {"c", "a", "e", "b", "d"};

        quickSort(stringArray, 0, stringArray.length - 1);

        //Output => a  b  c  d  e
        for (String str : stringArray) {
            System.out.print(str + "\t");
        }
    }
}

