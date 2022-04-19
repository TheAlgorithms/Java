package com.thealgorithms.sorts;

import java.lang.*;
import java.util.*;

public class PigeonholeSort {
    /*
        This code implements the pigeonhole sort algorithm for the integer array,
        but we can also implement this for string arrays too.
        See https://www.geeksforgeeks.org/pigeonhole-sort/
    */
    void sort(Integer[] array){
        int maxElement = array[0];
        for (int element: array) {
            if (element > maxElement) maxElement = element;
        }

        int numOfPigeonholes = 1 + maxElement;
        ArrayList<Integer>[] pigeonHole =  new ArrayList[numOfPigeonholes];

        for (int k=0; k<numOfPigeonholes; k++) {
            pigeonHole[k] = new ArrayList<>();
        }

        for (int t: array) {
            pigeonHole[t].add(t);
        }

        int k=0;
        for (ArrayList<Integer> ph: pigeonHole) {
            for (int elements: ph) {
                array[k]=elements;
                k=k+1;
            }
        }
    }

    public static void main(String[] args)
    {
        PigeonholeSort pigeonholeSort = new PigeonholeSort();
        Integer[] arr = { 8, 3, 2, 7, 4, 6, 8 };

        System.out.print("Unsorted: ");
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");

        pigeonholeSort.sort(arr);
        System.out.print("\nSorted order is : ");
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
    }
}
