package array;

import array.security.ProjectAlgorithmsScanner;

public class Quicksort {
    public static void main(String[] args) {
        int[] array;
        int size = 0;

        //Prompt user to create array and its elements
        System.out.print("Enter the size of the array: ");
        size = ProjectAlgorithmsScanner.getInteger();
        array = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.print("For index " + i + ", give an integer input: ");
            array[i] = ProjectAlgorithmsScanner.getInteger();
        }

        //Output inputted array
        System.out.println("The array is: ");
        printArray(array);
        System.out.println();

        //Run quicksort, and output sorted array
        quicksort(array, 0, array.length - 1);
        System.out.println("The sorted array is: ");
        printArray(array);
        System.out.println();
    }

    //Quicksort Method
    public static void quicksort(int[] ar, int start, int end) {
        int i = start, j = end;
        if (end - start >= 1) {
            int pivot = ar[end];
            while (i < j) {
                while (ar[i] < pivot && i < end) {
                    i++;
                }
                while (ar[j] >= pivot && j > start) {
                    j--;
                }
                if (i < j) {
                    swap(ar, i, j);
                }
            }
            swap(ar, end, i);

            quicksort(ar, start, i - 1);
            quicksort(ar, i + 1, end);
        }
    }

    //Helper methods
    public static void swap(int[] ar, int index1, int index2) {

        int temp = ar[index1];
        ar[index1] = ar[index2];
        ar[index2] = temp;
    }

    public static void printArray(int[] array) {

        for (int data : array) {
            System.out.print(data + " ");
        }
    }
}
