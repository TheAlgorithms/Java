package array;

import array.security.ProjectAlgorithmsScanner;

public class LinearSearch {
    //Main method
    public static void main(String[] args) {

        int[] myArray;
        int size;

        //Prompt user to create array and its elements
        System.out.print("Enter the array size: ");
        size = ProjectAlgorithmsScanner.getInteger();
        myArray = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.print("For index " + i + ", enter an integer: ");
            myArray[i] = ProjectAlgorithmsScanner.getInteger();
        }

        //Prompt user to search for particular element
        System.out.print("Enter integer to search for: ");
        int key = ProjectAlgorithmsScanner.getInteger();

        //Output array and index of target element, if found
        printArray(myArray);
        System.out.printf("The integer %d is found in index %d\n", key, linearSearch(myArray, key));

    }

    //Linear search method
    public static int linearSearch(int[] list, int key) {

        for (int i = 0; i < list.length; i++) {
            if (list[i] == key) {
                return i;
            }

        }
        return -1;
    }

    //Helper method
    public static void printArray(int[] a) {
        System.out.print("The array is: ");
        for (int d : a) {
            System.out.print(d + " ");
        }
        System.out.println();
    }
}