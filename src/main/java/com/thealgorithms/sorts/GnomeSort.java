package com.thealgorithms.sorts;

import static com.thealgorithms.sorts.SortUtils.*;

/**
 * Implementation of gnome sort
 *
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 * @since 2018-04-10
 */
public class GnomeSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] arr) {
        int i = 1;
        int j = 2;
        while (i < arr.length) {
            if (less(arr[i - 1], arr[i])) {
                i = j++;
            } else {
                swap(arr, i - 1, i);
                if (--i == 0) {
                    i = j++;
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        Integer[] integers = {
            4,
            23,
            6,
            78,
            1,
            26,
            11,
            23,
            0,
            -6,
            3,
            54,
            231,
            9,
            12,
        };
        String[] strings = {
            "c",
            "a",
            "e",
            "b",
            "d",
            "dd",
            "da",
            "zz",
            "AA",
            "aa",
            "aB",
            "Hb",
            "Z",
        };
        GnomeSort gnomeSort = new GnomeSort();

        gnomeSort.sort(integers);
        gnomeSort.sort(strings);

        System.out.println("After sort : ");
        print(integers);
        print(strings);
    }
}
