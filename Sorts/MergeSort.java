/**
 *
 * @author Varun Upadhyay (https://github.com/varunu28)
 *
 */

class MergeSort {

    /**
     * This method implements the Generic Merge Sort
     *
     * @param arr The array to be sorted
     * @param temp The copy of the actual array
     * @param left The first index of the array
     * @param right The last index of the array
     * Recursively sorts the array in increasing order
     **/

    public static <T extends Comparable<T>> void MS(T[] arr, T[] temp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            MS(arr, temp, left, mid);
            MS(arr,  temp,mid + 1, right);
            merge(arr, temp, left, mid, right);
        }

    }

    /**
     * This method implements the merge step of the merge sort
     *
     * @param arr The array to be sorted
     * @param temp The copy of the actual array
     * @param left The first index of the array
     * @param mid The middle index of the array
     * @param right The last index of the array
     * merges two parts of an array in increasing order
     **/

    public static <T extends Comparable<T>> void merge(T[] arr, T[] temp, int left, int mid, int right) {
        for (int i=left;i<=right;i++) {
            temp[i] = arr[i];
        }

        int i= left;
        int j = mid + 1;
        int k = left;

        while (i<=mid && j<=right) {
            if (temp[i].compareTo(temp[j]) <= 0) {
                arr[k] = temp[i];
                i++;
            }
            else {
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
        int[] arr = {4,23,6,78,1,54,231,9,12};
        Integer[] array = new Integer[arr.length];
        for (int i=0;i<array.length;i++) {
            array[i] = arr[i];
        }

        // Copy of actual array
        Integer[] temp = new Integer[arr.length];

        MS(array, temp, 0, arr.length-1);

        // Output => 1	   4  	 6	9	12	23	54	78	231
        for (int i=0;i<arr.length;i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();

        // String Input
        String[] array1 = {"c", "a", "e", "b","d"};
        String[] temp1 = new String[array1.length];
        MS(array1, temp1, 0, array1.length-1);

        //Output => a	b	c	d	e
        for(int i=0; i<array1.length; i++)
        {
            System.out.print(array1[i]+"\t");
        }
    }
}
