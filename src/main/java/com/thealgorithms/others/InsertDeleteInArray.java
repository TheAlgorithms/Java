package com.thealgorithms.others;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Utility class for performing insert and delete operations on arrays.
 * <p>
 * This class demonstrates how to insert an element at a specific position and
 * delete an element from a specific position in an integer array. Since arrays
 * in Java have fixed size, insertion creates a new array with increased size,
 * and deletion shifts elements to fill the gap.
 * </p>
 *
 * <p>
 * <strong>Time Complexity:</strong>
 * </p>
 * <ul>
 * <li>Insert: O(n) - requires copying elements to new array</li>
 * <li>Delete: O(n) - requires shifting elements</li>
 * </ul>
 *
 * <p>
 * <strong>Space Complexity:</strong>
 * </p>
 * <ul>
 * <li>Insert: O(n) - new array of size n+1</li>
 * <li>Delete: O(1) - in-place modification (excluding result array)</li>
 * </ul>
 *
 * @author TheAlgorithms community
 * @see <a href="https://en.wikipedia.org/wiki/Array_(data_structure)">Array
 *      Data Structure</a>
 */
public final class InsertDeleteInArray {
    private InsertDeleteInArray() {
    }

    /**
     * Inserts an element at the specified position in the array.
     * <p>
     * Creates a new array with size = original array size + 1.
     * Elements at positions &lt;= insertPos retain their positions,
     * while elements at positions &gt; insertPos are shifted right by one position.
     * </p>
     *
     * @param array    the original array
     * @param element  the element to be inserted
     * @param position the index at which the element should be inserted (0-based)
     * @return a new array with the element inserted at the specified position
     * @throws IllegalArgumentException if position is negative or greater than
     *                                  array length
     * @throws IllegalArgumentException if array is null
     */
    public static int[] insertElement(int[] array, int element, int position) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (position < 0 || position > array.length) {
            throw new IllegalArgumentException("Position must be between 0 and " + array.length);
        }

        int[] newArray = new int[array.length + 1];

        // Copy elements before insertion position
        System.arraycopy(array, 0, newArray, 0, position);

        // Insert the new element
        newArray[position] = element;

        // Copy remaining elements after insertion position
        System.arraycopy(array, position, newArray, position + 1, array.length - position);

        return newArray;
    }

    /**
     * Deletes an element at the specified position from the array.
     * <p>
     * Creates a new array with size = original array size - 1.
     * Elements after the deletion position are shifted left by one position.
     * </p>
     *
     * @param array    the original array
     * @param position the index of the element to be deleted (0-based)
     * @return a new array with the element at the specified position removed
     * @throws IllegalArgumentException if position is negative or greater than or
     *                                  equal to array length
     * @throws IllegalArgumentException if array is null or empty
     */
    public static int[] deleteElement(int[] array, int position) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (array.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        if (position < 0 || position >= array.length) {
            throw new IllegalArgumentException("Position must be between 0 and " + (array.length - 1));
        }

        int[] newArray = new int[array.length - 1];

        // Copy elements before deletion position
        System.arraycopy(array, 0, newArray, 0, position);

        // Copy elements after deletion position
        System.arraycopy(array, position + 1, newArray, position, array.length - position - 1);

        return newArray;
    }

    /**
     * Main method demonstrating insert and delete operations on an array.
     * <p>
     * This method interactively:
     * <ol>
     * <li>Takes array size and elements as input</li>
     * <li>Inserts a new element at a specified position</li>
     * <li>Deletes an element from a specified position</li>
     * <li>Displays the array after each operation</li>
     * </ol>
     * </p>
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Input: array size and elements
            System.out.println("Enter the size of the array:");
            int size = scanner.nextInt();

            if (size <= 0) {
                System.out.println("Array size must be positive");
                return;
            }

            int[] array = new int[size];

            System.out.println("Enter " + size + " elements:");
            for (int i = 0; i < size; i++) {
                array[i] = scanner.nextInt();
            }

            System.out.println("Original array: " + Arrays.toString(array));

            // Insert operation
            System.out.println("\nEnter the index at which the element should be inserted (0-" + size + "):");
            int insertPos = scanner.nextInt();
            System.out.println("Enter the element to be inserted:");
            int elementToInsert = scanner.nextInt();

            try {
                array = insertElement(array, elementToInsert, insertPos);
                System.out.println("Array after insertion: " + Arrays.toString(array));
            } catch (IllegalArgumentException e) {
                System.out.println("Error during insertion: " + e.getMessage());
                return;
            }

            // Delete operation
            System.out.println("\nEnter the index at which element is to be deleted (0-" + (array.length - 1) + "):");
            int deletePos = scanner.nextInt();

            try {
                array = deleteElement(array, deletePos);
                System.out.println("Array after deletion: " + Arrays.toString(array));
            } catch (IllegalArgumentException e) {
                System.out.println("Error during deletion: " + e.getMessage());
            }
        }
    }
}
