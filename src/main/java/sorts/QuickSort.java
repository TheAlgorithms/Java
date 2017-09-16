/**
 *
 * @author Varun Upadhyay (https://github.com/varunu28)
 *
 */

class QuickSort {

    /**
     * This method implements the Generic Quick Sort
     *
     * @param array The array to be sorted
     * @param start The first index of an array
     * @param end The last index of an array
     * Sorts the array in increasing order
     **/

    public static <T extends Comparable<T>> void QS(T array[], int start, int end) {
        if (start < end) {
            int PIndex = partition(array, start, end);
            QS(array, start, PIndex - 1);
            QS(array, PIndex + 1, end);
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

    // Driver Program
    public static void main(String[] args) {

        // For integer input
        int[] arr = {3,4,1,32,0,2,44,111,5};
        Integer[] array = new Integer[arr.length];
        for (int i=0;i<arr.length;i++) {
            array[i] = arr[i];
        }

        QS(array, 0, arr.length-1);

        //Output => 0 1 2 3 4 5 32 44 111
        for (int i=0;i<array.length;i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();

        // String Input
        String[] array1 = {"c", "a", "e", "b","d"};

        QS(array1, 0,array1.length-1);

        //Output => a	b	c	d	e
        for(int i=0; i<array1.length; i++)
        {
            System.out.print(array1[i]+"\t");
        }
    }
}

