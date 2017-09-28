package sorts;

import static sorts.MergeSort.sort;

public class MergeSortTest {

    public static void main(String[] args) {
        int[] arr = {4, 23, 6, 78, 1, 54, 231, 9, 12};
        Integer[] array = new Integer[arr.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = arr[i];
        }
        Integer[] temp = new Integer[arr.length];
        sort(array, temp, 0, arr.length - 1);
        // Output => 1	   4  	 6	9	12	23	54	78	231
        for (int i = 0; i < arr.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();

        String[] array1 = {"c", "a", "e", "b", "d"};
        String[] temp1 = new String[array1.length];
        sort(array1, temp1, 0, array1.length - 1);
        //Output => a	b	c	d	e
        for (int i = 0; i < array1.length; i++) {
            System.out.print(array1[i] + "\t");
        }
    }
}