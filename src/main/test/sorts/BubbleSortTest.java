package sorts;

import static sorts.BubbleSort.sort;

public class BubbleSortTest {

    public static void main(String[] args) {
        int[] arr1 = {4, 23, 6, 78, 1, 54, 231, 9, 12};
        int last = arr1.length;
        Integer[] array = new Integer[last];
        for (int i = 0; i < last; i++) {
            array[i] = arr1[i];
        }
        sort(array, last);
        // Output => 1	  4	 6	9	12	23	54	78	231
        for (int i = 0; i < last; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();

        String[] array1 = {"c", "a", "e", "b", "d"};
        last = array1.length;
        sort(array1, last);
        //Output => a	  b	 c	d	e
        for (int i = 0; i < last; i++) {
            System.out.print(array1[i] + "\t");
        }
    }
}