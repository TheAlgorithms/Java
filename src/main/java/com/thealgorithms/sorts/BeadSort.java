package com.thealgorithms.sorts;

// BeadSort Algorithm(wikipedia) : https://en.wikipedia.org/wiki/Bead_sort
// BeadSort can't sort negative number, Character, String. It can sort positive
// number only

public class BeadSort {
  public int[] sort(int[] unsorted) {

    // Determine the maximum value in the input array
    int[] sorted = new int[unsorted.length];
    int max = 0;
    for (int i = 0; i < unsorted.length; i++) {
      max = Math.max(max, unsorted[i]);
    }

    // Create a grid to represent the beads falling
    char[][] grid = new char[unsorted.length][max];

    // Create an array to keep track of the count of beads at each level
    int[] count = new int[max];

    // Initialize the grid with '-' (empty slots) and count array to 0
    for (int i = 0; i < unsorted.length; i++) {
      for (int j = 0; j < max; j++) {
        grid[i][j] = '-';
      }
    }

    for (int i = 0; i < max; i++) {
      count[i] = 0;
    }

    // Place beads on the grid based on the values in the input array
    for (int i = 0; i < unsorted.length; i++) {
      int k = 0;
      for (int j = 0; j < unsorted[i]; j++) {

        // Place '*' (beads) in the grid slots
        grid[count[max - k - 1]++][k] = '*';
        k++;
      }
    }

    // Count the number of beads in each column to determine the sorted order
    for (int i = 0; i < unsorted.length; i++) {
      int k = 0;
      for (int j = 0; j < max && grid[unsorted.length - 1 - i][j] == '*'; j++) {
        k++;
      }
      // Set the sorted value based on the count of beads
      sorted[i] = k;
    }

    // Return the sorted array
    return sorted;
  }
}
