package array;

import array.security.ProjectAlgorithmsScanner;

public class SelectionSort {
    public static void main(String[] args) {
        int array[] = new int[6];

        //Input
        System.out.println("Enter any 6 Numbers for Unsorted Array : ");
        for (int i = 0; i < 6; i++) {
            array[i] = ProjectAlgorithmsScanner.getInteger();
        }

        //Sorting
        for (int i = 0; i < 6; i++) {
            int min = i;
            for (int j = i + 1; j < 6; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            int temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }

        //Output
        for (int i = 0; i < 6; i++) {
            System.out.print(array[i] + "\t");
        }

    }
}
