package com.thealgorithms.sorts;

import java.util.Collections;

public class DutchNationalFlagSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        System.out.println(unsorted[(int) Math.floor((unsorted.length -1)/2.0)]);

        return dutch_national_flag_sort(unsorted, unsorted[(int) Math.floor((unsorted.length -1)/2.0)]);
    }

    public <T extends Comparable<T>> T[] sort(T[] unsorted, T intendedMiddle) {
        return dutch_national_flag_sort(unsorted, intendedMiddle);
    }

    private <T extends Comparable<T>> T[] dutch_national_flag_sort(T[] arr, T intendedMiddle){
        int i = 0;
        int j = 0;
        int k = arr.length - 1;

        while( j <= k){
            // arr[j] is more than middle
            if ( 0 < arr[j].compareTo(intendedMiddle)){
                SortUtils.swap(arr, i, j);
                j++;
                i++;
                // arr[j] is more than middle
            } else if (0 > arr[j].compareTo(intendedMiddle)){
                SortUtils.swap(arr, j, k);
                k--;
            } else {
                j++;
            }
        }
       return arr;
    }

    public static void main(String[] args){
        DutchNationalFlagSort dnfs = new DutchNationalFlagSort();

        System.out.println("First: Integer[] without giving a intended middle value.\n " +
                "Function will use the value positioned in the middle as intended middle value.");
        Integer[] integerArray0 = {40, 4, 8, 10, 15, 9, 11};
        // Print integerArray unsorted
        SortUtils.print(integerArray0);

        System.out.println("Sencond: Integer[] with giving a intended middle value.\n " +
                "Inended middle value is 16.");
        Integer[] integerArray1 = {16, 22, 8, 1, 16, 9, 2};
        // Print integerArray unsorted
        SortUtils.print(integerArray1);

        dnfs.sort(integerArray1, 16);
        // Print integerArray sorted
        SortUtils.print(integerArray1);

        System.out.println("Second: String[] with giving a intended middle value.\n " +
                "Intended middle value is \"F\".");
        String[] stringArray = {"D", "A", "F", "B", "E", "F", "C", "G"};
        // Print integerArray unsorted
        SortUtils.print(stringArray);

        dnfs.sort(stringArray, "F");
        // Print integerArray sorted
        SortUtils.print(stringArray);


    }
}
