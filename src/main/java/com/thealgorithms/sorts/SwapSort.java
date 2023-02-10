package com.thealgorithms.sorts;

import static com.thealgorithms.sorts.SortUtils.*;

/**
 * The idea of Swap-Sort is to count the number m of smaller values (that are in
 * A) from each element of an array A(1...n) and then swap the element with the
 * element in A(m+1). This ensures that the exchanged element is already in the
 * correct, i.e. final, position. The disadvantage of this algorithm is that
 * each element may only occur once, otherwise there is no termination.
 */
public class SwapSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        int LENGTH = array.length;
        int index = 0;

        while (index < LENGTH - 1) {
            int amountSmallerElements =
                this.getSmallerElementCount(array, index);

            if (amountSmallerElements > 0 && index != amountSmallerElements) {
                T element = array[index];
                array[index] = array[amountSmallerElements];
                array[amountSmallerElements] = element;
            } else {
                index++;
            }
        }

        return array;
    }

    private <T extends Comparable<T>> int getSmallerElementCount(
        T[] array,
        int index
    ) {
        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            if (less(array[i], array[index])) {
                counter++;
            }
        }

        return counter;
    }

    public static void main(String[] args) {
        // ==== Int =======
        Integer[] a = { 3, 7, 45, 1, 33, 5, 2, 9 };
        System.out.print("unsorted: ");
        print(a);
        System.out.println();

        new SwapSort().sort(a);
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

        new SwapSort().sort(b);
        System.out.print("sorted: ");
        print(b);
    }
}
