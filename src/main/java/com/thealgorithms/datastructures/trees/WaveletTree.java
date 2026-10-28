package com.thealgorithms.datastructures.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * A Wavelet Tree is a highly efficient data structure used to store sequences
 * and answer queries like rank, select, and quantile in O(log(max_val - min_val)) time.
 * This structure is particularly useful in competitive programming and text compression.
 */
public class WaveletTree {

    private class Node {
        int low;
        int high;
        Node left;
        Node right;
        List<Integer> leftCount; // Prefix sums of elements going to the left child

        /**
         * Recursively constructs the tree nodes by partitioning the array.
         *
         * @param arr  the subarray for the current node
         * @param low  the minimum possible value in the current node
         * @param high the maximum possible value in the current node
         */
        Node(int[] arr, int low, int high) {
            this.low = low;
            this.high = high;

            if (low == high) {
                return;
            }

            int mid = low + (high - low) / 2;
            leftCount = new ArrayList<>(arr.length + 1);
            leftCount.add(0);

            List<Integer> leftArr = new ArrayList<>();
            List<Integer> rightArr = new ArrayList<>();

            for (int x : arr) {
                if (x <= mid) {
                    leftArr.add(x);
                    leftCount.add(leftCount.get(leftCount.size() - 1) + 1);
                } else {
                    rightArr.add(x);
                    leftCount.add(leftCount.get(leftCount.size() - 1));
                }
            }

            if (!leftArr.isEmpty()) {
                this.left = new Node(leftArr.stream().mapToInt(i -> i).toArray(), low, mid);
            }
            if (!rightArr.isEmpty()) {
                this.right = new Node(rightArr.stream().mapToInt(i -> i).toArray(), mid + 1, high);
            }
        }
    }

    private Node root;
    private final int n;

    /**
     * Constructs a Wavelet Tree from the given array.
     * The min and max values are determined dynamically from the array.
     *
     * @param arr the input array
     */
    public WaveletTree(int[] arr) {
        if (arr == null || arr.length == 0) {
            this.n = 0;
            return;
        }
        this.n = arr.length;
        int min = arr[0];
        int max = arr[0];
        for (int x : arr) {
            if (x < min) {
                min = x;
            }
            if (x > max) {
                max = x;
            }
        }
        root = new Node(arr, min, max);
    }

    /**
     * Constructs a Wavelet Tree from the given array with specific min and max values.
     *
     * @param arr      the input array
     * @param minValue the minimum possible value
     * @param maxValue the maximum possible value
     */
    public WaveletTree(int[] arr, int minValue, int maxValue) {
        if (arr == null || arr.length == 0) {
            this.n = 0;
            return;
        }
        this.n = arr.length;
        root = new Node(arr, minValue, maxValue);
    }

    /**
     * How many times does the number x appear in the array from index 0 to i (inclusive)?
     *
     * @param x the number to search for
     * @param i the end index (0-based, inclusive)
     * @return the number of occurrences of x in arr[0...i]
     */
    public int rank(int x, int i) {
        if (root == null || x < root.low || x > root.high || i < 0) {
            return 0;
        }
        // If i is out of bounds, cap it at n - 1
        int endIdx = Math.min(i, n - 1);
        return rank(root, x, endIdx + 1);
    }

    private int rank(Node node, int x, int count) {
        if (node == null || count == 0) {
            return 0;
        }
        if (node.low == node.high) {
            return count;
        }
        int mid = node.low + (node.high - node.low) / 2;
        int leftC = node.leftCount.get(count);
        if (x <= mid) {
            return rank(node.left, x, leftC);
        } else {
            return rank(node.right, x, count - leftC);
        }
    }

    /**
     * What is the 0-based index of the k-th occurrence of the number x in the array?
     *
     * @param x the number to search for
     * @param k the occurrence count (1-based)
     * @return the 0-based index in the original array, or -1 if x occurs less than k times
     */
    public int select(int x, int k) {
        if (root == null || x < root.low || x > root.high || k <= 0) {
            return -1;
        }
        if (rank(x, n - 1) < k) {
            return -1;
        }
        return select(root, x, k);
    }

    private int select(Node node, int x, int k) {
        if (node.low == node.high) {
            return k - 1; // 0-based index within the imaginary array at the leaf
        }
        int mid = node.low + (node.high - node.low) / 2;
        if (x <= mid) {
            int posInLeft = select(node.left, x, k);
            return binarySearchLeft(node.leftCount, posInLeft + 1);
        } else {
            int posInRight = select(node.right, x, k);
            return binarySearchRight(node.leftCount, posInRight + 1);
        }
    }

    private int binarySearchLeft(List<Integer> prefixSums, int k) {
        int l = 1;
        int r = prefixSums.size() - 1;
        int ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (prefixSums.get(mid) >= k) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans == -1 ? -1 : ans - 1; // Convert to 0-based index
    }

    private int binarySearchRight(List<Integer> prefixSums, int k) {
        int l = 1;
        int r = prefixSums.size() - 1;
        int ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (mid - prefixSums.get(mid) >= k) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans == -1 ? -1 : ans - 1; // Convert to 0-based index
    }

    /**
     * If you sort the subarray from index left to right, what would be the k-th smallest element?
     * This query is also commonly known as the quantile query.
     *
     * @param left  the start index of the subarray (0-based, inclusive)
     * @param right the end index of the subarray (0-based, inclusive)
     * @param k     the rank of the smallest element (1-based, e.g., k=1 is the minimum)
     * @return the k-th smallest element in the subarray, or -1 if invalid parameters
     */
    public int kthSmallest(int left, int right, int k) {
        if (root == null || left > right || left < 0 || k < 1 || k > right - left + 1) {
            return -1;
        }
        return kthSmallest(root, left, right, k);
    }

    private int kthSmallest(Node node, int left, int right, int k) {
        if (node.low == node.high) {
            return node.low;
        }

        int countLeftInLMinus1 = (left == 0) ? 0 : node.leftCount.get(left);
        int countLeftInR = node.leftCount.get(right + 1);
        int elementsToLeft = countLeftInR - countLeftInLMinus1;

        if (k <= elementsToLeft) {
            int newL = countLeftInLMinus1;
            int newR = countLeftInR - 1;
            return kthSmallest(node.left, newL, newR, k);
        } else {
            int newL = left - countLeftInLMinus1;
            int newR = right - countLeftInR;
            return kthSmallest(node.right, newL, newR, k - elementsToLeft);
        }
    }
}
