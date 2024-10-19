import java.util.*;

public class UbiquitousSearch {
    
    // Method to perform Ubiquitous Search
    public static int ubiquitousSearch(int[] arr, int target) {
        int l = 0, r = arr.length - 1;
        
        while (l <= r) {
            // Find the middle index
            int m = l + (r - l) / 2;
            
            // Check if the middle element is the target
            if (arr[m] == target) {
                return m; // Target found
            }

            // If the right half is sorted
            if (arr[m] < arr[r]) {
                // Check if the target is in the sorted right half
                if (arr[m] < target && target <= arr[r]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            // If the left half is sorted
            else {
                // Check if the target is in the sorted left half
                if (arr[l] <= target && target < arr[m]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
        }
        
        // Target not found
        return -1;
    }

    public static void main(String[] args) {
        // Example usage
        int[] arr = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;

        int result = ubiquitousSearch(arr, target);
        if (result != -1) {
            System.out.println("Target found at index: " + result);
        } else {
            System.out.println("Target not found");
        }
    }
}
