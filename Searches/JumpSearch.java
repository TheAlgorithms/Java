/**
 * Java class to implement the JumpSearch algorithm.
 * JumpSearch searches a sorted array by making jumps of size sqrt(n), then,
 * if the value is less than the desired value, do a linear search on the last block jumped.
 *
 * JumpSearch's time complexity is O(sqrt(n)), but has an advantage over BinarySearch
 * in systems where it is costly to go backwards in the data.
 */
public class JumpSearch {
    public static int jumpSearch(int[] arr, int x) {
        int len = arr.length;

        // Find block size to be jumped
        int jump = (int)Math.floor(Math.sqrt(len));

        // Find the block where element is present (if it is present)
        int index = jump;
        int prev = 0;
        while (arr[Math.min(jump, len)-1] < x) {
            prev = index;
            index += jump;
            if (prev >= len)
                return -1;
        }

        // Doing a linear search for x in block beginning with prev.
        while (arr[prev] < x) {
            prev++;

            // If we reached next block or end of array, element is not present.
            if (prev == Math.min(jump, len))
                return -1;
        }

        // If element is found
        if (arr[prev] == x)
            return prev;

        return -1;
    }
}
