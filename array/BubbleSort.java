package array;

import array.security.ProjectAlgorithmsScanner;

class BubbleSort {
    public static void main(String[] args) {
        int array[] = new int[6];

        //Input
        System.out.println("Enter any 6 Numbers for Unsorted Array : ");
        for (int i = 0; i < 6; i++) {
            array[i] = ProjectAlgorithmsScanner.getInteger();
        }

        //Sorting
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        //Output
        for (int i = 0; i < 6; i++) {
            System.out.print(array[i] + "\t");
        }

    }

}