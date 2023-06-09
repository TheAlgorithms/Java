package com.thealgorithms.sorts;

// BeadSort Algorithm(wikipedia) : https://en.wikipedia.org/wiki/Bead_sort
// BeadSort can't sort negative number, Character, String. It can sort positive number only

public class BeadSort {
    public int[] sort(int[] unsorted) {
        int[] sorted = new int[unsorted.length];
        int max = 0;
        for (int i = 0; i < unsorted.length; i++) {
            max = Math.max(max, unsorted[i]);
        }

        char[][] grid = new char[unsorted.length][max];
        int[] count = new int[max];

        for (int i = 0; i < unsorted.length; i++) {
            for (int j = 0; j < max; j++) {
                grid[i][j] = '-';
            }
        }

        for (int i = 0; i < max; i++) {
            count[i] = 0;
        }

        for (int i = 0; i < unsorted.length; i++) {
            int k = 0;
            for (int j = 0; j < unsorted[i]; j++) {
                grid[count[max - k - 1]++][k] = '*';
                k++;
            }
        }

        for (int i = 0; i < unsorted.length; i++) {
            int k = 0;
            for (int j = 0; j < max && grid[unsorted.length - 1 - i][j] == '*'; j++) {
                k++;
            }
            sorted[i] = k;
        }
        return sorted;
    }
}
