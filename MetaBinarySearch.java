public class MetaBinarySearch {

    // Function to perform Meta Binary Search
    public static int metaBinarySearch(int[] arr, int target) {
        int n = arr.length;
        int left = 0;
        int right = n - 1;

        // Calculate the number of bits required for the maximum index
        int maxBits = (int) Math.ceil(Math.log(n) / Math.log(2));

        // Iterate over the bit length
        for (int i = maxBits - 1; i >= 0; i--) {
            int mid = left + (1 << i);

            // Check if mid index is within bounds
            if (mid < n && arr[mid] <= target) {
                left = mid; // move to the right half if condition is true
            }
        }

        // Check if we have found the target
        if (arr[left] == target) {
            return left;
        }

        // If target not found
        return -1;
    }

    // Main function to test the Meta Binary Search
    public static void main(String[] args) {
        int[] sortedArray = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21};
        int target = 13;

        int result = metaBinarySearch(sortedArray, target);
        if (result != -1) {
            System.out.println("Target element " + target + " found at index: " + result);
        } else {
            System.out.println("Target element " + target + " not found in the array.");
        }
    }
}
