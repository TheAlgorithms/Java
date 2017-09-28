package sorts;

import static sorts.QuickSort.sort;

public class QuickSortTest {

    public static void main(String[] args) {
        int[] arr = {3, 4, 1, 32, 0, 2, 44, 111, 5};
        Integer[] array = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[i];
        }
        sort(array, 0, arr.length - 1);
        //Output => 0 1 2 3 4 5 32 44 111
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();

        String[] array1 = {"c", "a", "e", "b", "d"};
        sort(array1, 0, array1.length - 1);
        //Output => a	b	c	d	e
        for (int i = 0; i < array1.length; i++) {
            System.out.print(array1[i] + "\t");
        }
    }
}