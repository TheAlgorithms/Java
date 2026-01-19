package com.thealgorithms.searches;

/**
 * Binary Search implementation specifically for String arrays
 * This algorithm finds the position of a target string within a sorted string array
 * 
 * Time Complexity: O(log n * m) where n is array length and m is average string length
 * Space Complexity: O(1)
 * 
 * @see <a href="https://en.wikipedia.org/wiki/Binary_search_algorithm">Binary Search Algorithm</a>
 * @author Jeevan Yewale (https://github.com/JeevanYewale)
 */
public final class BinarySearchStrings {

    private BinarySearchStrings() {
        // Utility class
    }

    /**
     * Performs binary search on a sorted string array
     * 
     * @param array sorted array of strings (must be sorted in lexicographical order)
     * @param target the string to search for
     * @return index of target string if found, -1 otherwise
     */
    public static int search(String[] array, String target) {
        if (array == null || array.length == 0 || target == null) {
            return -1;
        }
        
        int left = 0;
        int right = array.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = target.compareTo(array[mid]);
            
            if (comparison == 0) {
                return mid; // Found the target
            } else if (comparison < 0) {
                right = mid - 1; // Target is in left half
            } else {
                left = mid + 1; // Target is in right half
            }
        }
        
        return -1; // Target not found
    }

    /**
     * Performs case-insensitive binary search on a sorted string array
     * 
     * @param array sorted array of strings (must be sorted in lexicographical order, case-insensitive)
     * @param target the string to search for
     * @return index of target string if found, -1 otherwise
     */
    public static int searchIgnoreCase(String[] array, String target) {
        if (array == null || array.length == 0 || target == null) {
            return -1;
        }
        
        int left = 0;
        int right = array.length - 1;
        String targetLower = target.toLowerCase();
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = targetLower.compareTo(array[mid].toLowerCase());
            
            if (comparison == 0) {
                return mid; // Found the target
            } else if (comparison < 0) {
                right = mid - 1; // Target is in left half
            } else {
                left = mid + 1; // Target is in right half
            }
        }
        
        return -1; // Target not found
    }
}