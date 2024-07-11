package com.thealgorithms.sorts;

public class SimpleSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        final int length = array.length;

        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (SortUtils.less(array[j], array[i])) {
                    SortUtils.swap(array, i, j);
                }
            }
        }

        return array;
    }

    public static void main(String[] args) {
        // ==== Int =======
        Integer[] a = {3, 7, 45, 1, 33, 5, 2, 9};
        System.out.print("unsorted: ");
        SortUtils.print(a);
        System.out.println();

        new SimpleSort().sort(a);
        System.out.print("sorted: ");
        SortUtils.print(a);
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
        SortUtils.print(b);
        System.out.println();

        new SimpleSort().sort(b);
        System.out.print("sorted: ");
        SortUtils.print(b);
    }
}
