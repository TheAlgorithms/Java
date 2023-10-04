package com.thealgorithms.searches;

class PerfectBinarySearch {

    static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assert PerfectBinarySearch.binarySearch(array, -1) == -1;
        assert PerfectBinarySearch.binarySearch(array, 11) == -1;
    }
}
