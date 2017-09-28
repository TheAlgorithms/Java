package sorts;

import static sorts.SelectionSort.sort;

public class SelectionSortTest {

    public static void main(String[] args) {
        int[] arr1 = {4, 23, 6, 78, 1, 54, 231, 9, 12};
        int n = arr1.length;
        Integer[] array = new Integer[n];
        for (int i = 0; i < n; i++) {
            array[i] = arr1[i];
        }
        sort(array, n);
        // Output => 1	  4	 6	9	12	23	54	78	231
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + "\t");
        }

        System.out.println();
        // String Input
        String[] array1 = {"c", "a", "e", "b", "d"};
        n = array1.length;
        sort(array1, n);
        //Output => a	b	 c  d	e
        for (int i = 0; i < n; i++) {
            System.out.print(array1[i] + "\t");
        }
    }
}