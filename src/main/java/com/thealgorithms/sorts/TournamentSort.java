package com.thealgorithms.sorts;

import java.util.Arrays;

public class TournamentSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        if (array == null || array.length <= 1) return array;
        T[] arr = Arrays.copyOf(array, array.length);
        int n = arr.length;
        T[] sorted = Arrays.copyOf(array, array.length);

        for (int i = 0; i < n; i++) {
            int size = nextPowerOfTwo(n - i);
            @SuppressWarnings("unchecked")
            T[] tree = (T[]) new Comparable[2 * size - 1];
            for (int j = 0; j < n - i; j++) tree[size - 1 + j] = arr[j];
            for (int j = size - 2; j >= 0; j--) {
                T left = tree[2 * j + 1], right = tree[2 * j + 2];
                if (left == null) tree[j] = right;
                else if (right == null) tree[j] = left;
                else tree[j] = left.compareTo(right) <= 0 ? left : right;
            }
            sorted[i] = tree[0];
            removeWinner(arr, sorted[i]);
        }
        return sorted;
    }

    private <T extends Comparable<T>> void removeWinner(T[] arr, T winner) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && arr[i].equals(winner)) {
                arr[i] = null;
                compact(arr);
                break;
            }
        }
    }

    private <T> void compact(T[] arr) {
        int index = 0;
        for (T element : arr) {
            if (element != null) arr[index++] = element;
        }
        while (index < arr.length) arr[index++] = null;
    }

    private int nextPowerOfTwo(int n) {
        int p = 1;
        while (p < n) p <<= 1;
        return p;
    }

    public static void main(String[] args) {
        TournamentSort sorter = new TournamentSort();
        Integer[] arr = {5, 3, 8, 4, 1, 2};
        Integer[] res = sorter.sort(arr);
        System.out.println(Arrays.toString(res));
    }
}
