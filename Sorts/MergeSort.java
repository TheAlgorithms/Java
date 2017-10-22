package Sorts;

/**
 * @author Varun Upadhyay (https://github.com/varunu28)
 */

class MergeSort {

    /**
     * This method implements the Generic Merge Sort. Recursively sorts the array in increasing order
     *
     * @param arr   The array to be sorted
     * @param temp  The copy of the actual array
     * @param left  The first index of the array
     * @param right The last index of the array
     **/

    public static <T extends Comparable<T>> void mergeSort(T[] arr, T[] temp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, temp, left, mid);
            mergeSort(arr, temp, mid + 1, right);
            merge(arr, temp, left, mid, right);
        }
    }

    /**
     * This method implements the merge step of the merge sort
     *
     * @param arr   The array to be sorted
     * @param temp  The copy of the actual array
     * @param left  The first index of the array
     * @param mid   The middle index of the array
     * @param right The last index of the array
     *              merges two parts of an array in increasing order
     **/

    public static <T extends Comparable<T>> void merge(T[] arr, T[] temp, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (temp[i].compareTo(temp[j]) <= 0) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
            k++;
        }

        while (i <= mid) {
            arr[k] = temp[i];
            i++;
            k++;
        }
    }

    // Driver program
    public static void main(String[] args) {

        // Integer Input
        Integer[] array = {4, 23, 6, 78, 1, 54, 231, 9, 12};

        // Copy of actual array
        Integer[] temp = new Integer[array.length];

        mergeSort(array, temp, 0, array.length - 1);

        // Output => 1 4 6 9 12 23 54 78 231
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();

        // String Input
        String[] stringArray = {"c", "a", "e", "b", "d"};
        String[] temp1 = new String[stringArray.length];
        mergeSort(stringArray, temp1, 0, stringArray.length - 1);

        //Output => a b c d e
        for (String str : stringArray) {
            System.out.print(str + "\t");
        }
    }
}
