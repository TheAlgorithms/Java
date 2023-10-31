package com.thealgorithms.divideandconquer;

class median_of_two_sorted_array {
    /*Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).

 

Example 1:

Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.
Example 2:

Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 

Constraints:

nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-106 <= nums1[i], nums2[i] <= 106 */
    // Helper function to find the kth element in merged sorted arrays
    private double findKthElement(int k, int[] nums1, int[] nums2, int start1, int end1, int start2, int end2) {
        // Base cases when one array is exhausted
        if (start1 >= end1)
            return nums2[start2 + k];
        if (start2 >= end2)
            return nums1[start1 + k];

        // Calculate mid indices
        int mid1 = (end1 - start1) / 2;
        int mid2 = (end2 - start2) / 2;

        // Compare mid elements and recurse accordingly
        if (mid1 + mid2 < k) {
            if (nums1[start1 + mid1] > nums2[start2 + mid2])
                // Discard elements before mid2 and search in the remaining array
                return findKthElement(k - mid2 - 1, nums1, nums2, start1, end1, start2 + mid2 + 1, end2);
            else
                // Discard elements before mid1 and search in the remaining array
                return findKthElement(k - mid1 - 1, nums1, nums2, start1 + mid1 + 1, end1, start2, end2);
        } else {
            if (nums1[start1 + mid1] > nums2[start2 + mid2])
                // Discard elements after mid1 and search in the remaining array
                return findKthElement(k, nums1, nums2, start1, start1 + mid1, start2, end2);
            else
                // Discard elements after mid2 and search in the remaining array
                return findKthElement(k, nums1, nums2, start1, end1, start2, start2 + mid2);
        }
    }

    // Function to find the median of merged sorted arrays
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size1 = nums1.length;
        int size2 = nums2.length;
        int totalSize = size1 + size2;

        // Check if the total size is odd or even and find the median accordingly
        if (totalSize % 2 == 1)
            // For odd total size, median is the kth element where k = total_size // 2
            return findKthElement(totalSize / 2, nums1, nums2, 0, size1, 0, size2);
        else {
            // For even total size, median is the average of kth and (kth - 1) elements
            double num1 = findKthElement(totalSize / 2, nums1, nums2, 0, size1, 0, size2);
            double num2 = findKthElement(totalSize / 2 - 1, nums1, nums2, 0, size1, 0, size2);

            return (num1 + num2) / 2.0;
        }
    }
}