package sorts;

import static sorts.InsertionSort.sort;

public class InsertionSortTest {

    public static void main(String[] args) {
        int[] arr1 = {4, 23, 6, 78, 1, 54, 231, 9, 12};
        int last = arr1.length;
        Integer[] array = new Integer[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            array[i] = arr1[i];
        }
        sort(array, last);
        // Output => 1 4 6 9 12 23 54 78 231
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();

        String[] array1 = {"c", "a", "e", "b", "d"};
        last = array1.length;
        sort(array1, last);
        //Output => a	b	c	d	e
        for (int i = 0; i < last; i++) {
            System.out.print(array1[i] + "\t");
        }
    }
}