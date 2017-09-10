/**
 *
 * @author Varun Upadhyay (https://github.com/varunu28)
 *
 */

public class SelectionSort {

    /**
     * This method implements the Generic Selection Sort
     *
     * @param arr The array to be sorted
     * @param n The count of total number of elements in array
     * Sorts the array in increasing order
     **/

    public static <T extends Comparable<T>> void SS(T[] arr, int n) {

        for (int i=0;i<n-1;i++) {

            // Initial index of min
            int min = i;

            for (int j=i+1;j<n;j++) {
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

    // Driver Program
    public static void main(String[] args) {

        // Integer Input
        int[] arr1 = {4,23,6,78,1,54,231,9,12};
        int n = arr1.length;

        Integer[] array = new Integer[n];
        for (int i=0;i<n;i++) {
            array[i] = arr1[i];
        }

        SS(array, n);

        // Output => 1	  4	 6	9	12	23	54	78	231
        for(int i=0; i<n; i++)
        {
            System.out.print(array[i]+"\t");
        }

        System.out.println();

        // String Input
        String[] array1 = {"c", "a", "e", "b","d"};
        n = array1.length;

        SS(array1, n);

        //Output => a	b	 c  d	e
        for(int i=0; i<n; i++)
        {
            System.out.print(array1[i]+"\t");
        }
    }
}
