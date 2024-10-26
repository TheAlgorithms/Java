package com.thealgorithms.misc;
import java.util.Random;
import java.util.Scanner;
/**
 * The Fisher-Yates (Knuth) Shuffle algorithm randomly permutes an array's
 * elements, ensuring each permutation is equally likely.
 *
 * <p>
 * Worst-case performance O(n)
 * Best-case performance O(n)
 * Average performance O(n)
 * Worst-case space complexity O(1)
 *
 * This class provides a static method to shuffle an array in place.
 *
 * @author Your Name (https://github.com/Chiefpatwal)
 */
public final class ShuffleArray {
    // Prevent instantiation
    private ShuffleArray() {
    }
    /**
     * This method shuffles an array using the Fisher-Yates algorithm.
     *
     * @param arr is the input array to be shuffled
     */
    public static void shuffle(int[] arr) {
        Random random = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
    /**
     * This method takes user input to create an array.
     *
     * @return the array created from user input
     */
    public static int[] getUserInputArray() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();

        int[] arr = new int[size];
        System.out.println("Enter " + size + " elements:");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }
        return arr;
    }
    public static void main(String[] args) {
        int[] userArray = getUserInputArray();
        System.out.println("Original Array:");
        printArray(userArray);

        shuffle(userArray);
        System.out.println("Shuffled Array:");
        printArray(userArray);
    }
    /**
     * This method prints the elements of the array.
     *
     * @param arr is the input array to be printed
     */
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
