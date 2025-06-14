/**
 * LinearSearch.java
 * This program implements the Linear Search algorithm.
 * Time Complexity: O(n)
 */

public class LinearSearch {

    public static int linearSearch(int[] arr, int target) {
        // Traverse each element in the array
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // Found the target at index i
            }
        }
        return -1; // Target not found
    }

    public static void main(String[] args) {
        int[] numbers = {5, 8, 12, 20, 35};
        int target = 20;

        int index = linearSearch(numbers, target);
        if (index != -1) {
            System.out.println("Target " + target + " found at index: " + index);
        } else {
            System.out.println("Target " + target + " not found in the array.");
        }
    }
}