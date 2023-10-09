package com.thealgorithms.sorts;

import static com.thealgorithms.sorts.SortUtils.*;

import java.util.*;

public class PigeonholeSort {

    /*
        This code implements the pigeonhole sort algorithm for the integer array,
        but we can also implement this for string arrays too.
        See https://www.geeksforgeeks.org/pigeonhole-sort/
    */
    void sort(Integer[] array) {
        int maxElement = array[0];
        for (int element : array) {
            if (element > maxElement) maxElement = element;
        }

        int numOfPigeonholes = 1 + maxElement;
        ArrayList<Integer>[] pigeonHole = new ArrayList[numOfPigeonholes];

        for (int k = 0; k < numOfPigeonholes; k++) {
            pigeonHole[k] = new ArrayList<>();
        }

        for (int t : array) {
            pigeonHole[t].add(t);
        }

        int k = 0;
        for (ArrayList<Integer> ph : pigeonHole) {
            for (int elements : ph) {
                array[k] = elements;
                k = k + 1;
            }
        }
    }

    public static void main(String[] args) {
        PigeonholeSort pigeonholeSort = new PigeonholeSort();
        Integer[] arr = {8, 3, 2, 7, 4, 6, 8};

        System.out.print("Unsorted order is : ");
        print(arr);

        pigeonholeSort.sort(arr);

        System.out.print("Sorted order is : ");
        for (int i = 0; i < arr.length; i++) {
            assert (arr[i]) <= (arr[i + 1]);
        }
        print(arr);
    }
}
