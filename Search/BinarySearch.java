/**
 * BinarySearch.java
 * This program implements recursive Binary Search on a sorted array.
 * Time Complexity: O(log n)
 */

public class BinarySearch {

    // Recursive method to perform binary search
    public static int binarySearch(int[] arr, int left, int right, int target) {
        if (left > right) {
            return -1;  // Base case: element not found
        }

        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return mid;  // Found the target
        } else if (target < arr[mid]) {
            return binarySearch(arr, left, mid - 1, target);  // Search in the left half
        } else {
            return binarySearch(arr, mid + 1, right, target);  // Search in the right half
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 8, 15, 23, 42, 56};
        int target = 23;

        int result = binarySearch(arr, 0, arr.length - 1, target);

        if (result != -1) {
            System.out.println("Target " + target + " found at index: " + result);
        } else {
            System.out.println("Target " + target + " not found in the array.");
        }
    }
}