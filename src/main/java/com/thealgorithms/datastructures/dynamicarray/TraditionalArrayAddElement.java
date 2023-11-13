package com.thealgorithms.datastructures.dynamicarray;

import java.util.*;

/**
 * The TraditionalArrayAddElement class provides a method for adding elements to an array in a traditional way.
 * It changes the size of an array as you add the elements.
 * https://docs.oracle.com/javase/specs/jls/se7/html/jls-10.html
 */
public class TraditionalArrayAddElement {

    /**
     * Adds an element to the end of the given array.
     *
     * @param arr The original array.
     * @param element The element to be added to the array.
     * @return A new array containing all elements from the original array plus the added element.
     */
    public int[] addElement(int[] arr, int element) {
        int[] temp = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }
        temp[arr.length] = element;
        return temp;
    }

    /**
     * The main method serves as a driver code to demonstrate the functionality of the addElement method.
     *
     * @param args The command line arguments (not used in this example).
     */
    public static void main(String[] args) {

        int[] ints = {1, 2, 3, 4};
        System.out.println("Initial array: " + Arrays.toString(ints));

        TraditionalArrayAddElement myObj = new TraditionalArrayAddElement();

        ints = myObj.addElement(ints, 5);
        ints = myObj.addElement(ints, 6);
        ints = myObj.addElement(ints, 7);
        ints = myObj.addElement(ints, 8);

        System.out.println("Final array: " + Arrays.toString(ints));
    }
}
