package Sorts;

import java.util.Arrays;
import java.util.List;
import static Sorts.SortUtils.less;
import static Sorts.SortUtils.print;

/**
 * This method implements the Generic Merge Sort
 *
 * @author Andreas Manos (https://github.com/Antwhite420)
 */
public class TimSort {

    public static void main(String[] args) {

        Integer[] arr = {4, 23, 6, 781, 1, 54, -231, 9, 12};    // Integer Input for sorting array
        List<Integer> ints = Arrays.asList(arr);    // Integer Input for sorting list

        TimSortAlgo timSort = new TimSortAlgo();

        timSort.sort(arr);
        timSort.sort(ints);

        System.out.println("Printing integer array: ");
        print(arr);     // prints out: [-231, 1, 4, 6, 9, 12, 23, 54, 781]
        System.out.println("Printing integer list: ");
        print(ints);    // prints out: -231 1 4 6 9 12 23 54 781


        String[] stringArray = {"Tommy", "Alex", "Sara", "Bob", "Daniel"};   // String Input for sorting list

        List<String> strings = Arrays.asList(stringArray);    // String Input for sorting list

        timSort.sort(stringArray);
        timSort.sort(strings);

        System.out.println("Printing String array: ");
        print(stringArray);     // prints out: [Alex, Bob, Daniel, Sara, Tommy]
        System.out.println("Printing String list: ");
        print(strings);     // prints out: Alex Bob Daniel Sara Tommy

    }

    /**
     * A private inner class that implements the SortAlgorithm interface
     * @see SortAlgorithm
     */
    private static class TimSortAlgo implements SortAlgorithm {

        private final int RUN = 32;

        /**
         * sort method sorts the given array
         *
         * @param unsorted - an array should be sorted
         * @return the sorted array
         */
        @Override
        public <T extends Comparable<T>> T[] sort(T[] unsorted) {
            for (int i = 0; i < unsorted.length; i += RUN) {
                insertionSort(unsorted, i, Math.min((i + 31), (unsorted.length - 1)));
            }

            for (int size = RUN; size < unsorted.length; size *= 2) {

                for (int left = 0; left < unsorted.length; left += 2 * size) {

                    int mid = left + size - 1;
                    int right = Math.min((left + 2 * size - 1), (unsorted.length - 1));
                    merge(unsorted, left, mid, right);
                }
            }
            return unsorted;
        }

        /**
         * sort method sorts the given list
         *
         * @param unsorted - a list should be sorted
         * @return return the sorted list
         */
        @Override
        public <T extends Comparable<T>> List<T> sort(List<T> unsorted) {
            for (int i = 0; i < unsorted.size(); i += RUN) {
                // The list is converted to array before is sorted
                insertionSort(unsorted.toArray((T[]) new Comparable[unsorted.size()]), i, Math.min((i + 31), (unsorted.size() - 1)));
            }

            for (int size = RUN; size < unsorted.size(); size *= 2) {

                for (int left = 0; left < unsorted.size(); left += 2 * size) {

                    int mid = left + size - 1;
                    int right = Math.min((left + 2 * size - 1), (unsorted.size() - 1));
                    // The list is converted to array before is merged
                    merge(unsorted.toArray((T[]) new Comparable[unsorted.size()]), left, mid, right);
                }
            }
            return unsorted;
        }

        /**
         * insertionSort method sorts the array from left index to right index
         *
         * @param arr the original array
         * @param left the first element of the array
         * @param right the middle element of the array
         */
        public <T extends Comparable<T>> void insertionSort(T[] arr, int left, int right) {
            for (int i = left + 1; i <= right; i++) {
                T temp = arr[i];
                int j = i - 1;
                while (j >= 0 && less(temp,arr[j])) {
                    arr[j + 1] = arr[j];
                    j--;
                }
                arr[j + 1] = temp;
            }
        }

        //

        /**
         * merge method merges the sorted RUNS in ascending order
         *
         * @param arr the original array
         * @param l the first element of the array
         * @param m the middle element of the array
         * @param r the last element of the array
         */
        public <T extends Comparable<T>> void merge(T[] arr, int l,
                                 int m, int r) {

            // The array is splitted into two arrays left and right
            int len1 = m - l + 1, len2 = r - m;
            T[] left = (T[]) new Comparable[len1];
            T[] right = (T[]) new Comparable[len2];

            // Passing the elements from the original array to the sub arrays
            if (len1 >= 0) System.arraycopy(arr, l, left, 0, len1);
            if (len2 >= 0) System.arraycopy(arr,m+1,right,0,len2);

            int i = 0;
            int j = 0;
            int k = l;

            // Passing the elements to the original array after comparing them
            while (i < len1 && j < len2) {
                if (less(left[i],right[j]) || left[i].compareTo(right[j]) == 0) {
                    arr[k] = left[i];
                    i++;
                } else {
                    arr[k] = right[j];
                    j++;
                }
                k++;
            }

            // Passing the remaining elements of left array, if any
            while (i < len1) {
                arr[k] = left[i];
                k++;
                i++;
            }

            // Passing the remaining element of right array, if any
            while (j < len2) {
                arr[k] = right[j];
                k++;
                j++;
            }
        }
    }
}
