package com.thealgorithms.shufflealgo;

import java.util.Random;

public
final class UnderstandingShuffleAlgo {

    private
    UnderstandingShuffleAlgo() {
        // Prevent instantiation
    }

    /**
     * Shuffles the elements in the array randomly.
     * Uses a method that gives each item an equal chance to appear in any
     * position.
     *
     * @param array the array to be shuffled
     */
    public
    static void shuffle(int[] array) {
        // Create a Random object to generate random numbers
        Random random = new Random();

        // Loop from the last element to the second element
        for (int i = array.length - 1; i > 0; i--) {
            // Generate a random index from 0 to i (inclusive)
            int j = random.nextInt(i + 1);

            // Swap the elements at positions i and j
            int temp = array[i]; // Temporarily store the element at i
            array[i] = array[j]; // Move element from j to i
            array[j] = temp;     // Place the stored element in position j
        }
    }

    /**
     * Main method to demonstrate the shuffle function.
     * Shows the array before and after shuffling.
     *
     * @param args command-line arguments (not used here)
     */
    public
    static void main(String[] args) {
        // Create an example array of numbers from 1 to 9
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        // Display the original array
        System.out.println("Original Array:");
        for (int num : array) {
            System.out.print(num + " ");
        }

        // Call the shuffle method to randomize the array
        shuffle(array);

        // Display the shuffled array
        System.out.println("\nShuffled Array:");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}
