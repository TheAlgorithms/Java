package com.thealgorithms.sorts;

import static com.thealgorithms.sorts.SortUtils.*;

public class SimpleSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        final int LENGTH = array.length;

        for (int i = 0; i < LENGTH; i++) {
            for (int j = i + 1; j < LENGTH; j++) {
                if (less(array[j], array[i])) {
                    T element = array[j];
                    array[j] = array[i];
                    array[i] = element;
                }
            }
        }

        return array;
    }

    public static void main(String[] args) {
        // ==== Int =======
        Integer[] a = { 3, 7, 45, 1, 33, 5, 2, 9 };
        System.out.print("unsorted: ");
        print(a);
        System.out.println();

        new SimpleSort().sort(a);
        System.out.print("sorted: ");
        print(a);
        System.out.println();

        // ==== String =======
        String[] b = {
            "banana",
            "berry",
            "orange",
            "grape",
            "peach",
            "cherry",
            "apple",
            "pineapple",
        };
        System.out.print("unsorted: ");
        print(b);
        System.out.println();

        new SimpleSort().sort(b);
        System.out.print("sorted: ");
        print(b);
    }
}
