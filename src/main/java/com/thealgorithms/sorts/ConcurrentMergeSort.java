package com.thealgorithms.sorts;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * A concurrent implementation of the Merge Sort algorithm.
 *
 * <p>This implementation utilizes a divide-and-conquer strategy, distributing
 * the sorting of sub-arrays across multiple threads using a {@link ThreadPoolExecutor}.
 * To prevent the overhead of thread creation and context switching from outweighing
 * the benefits of concurrency, it falls back to a standard sequential merge sort
 * when the sub-array size drops below a predefined threshold, or when the maximum
 * concurrency depth is reached (preventing thread starvation deadlocks).
 *
 * <p><strong>Complexity:</strong>
 * <ul>
 *   <li>Time Complexity: $O(N \log N)$</li>
 *   <li>Space Complexity: $O(N)$</li>
 * </ul>
 */
public final class ConcurrentMergeSort {

    private ConcurrentMergeSort() {
    }

    /**
     * Fallback threshold where the algorithm switches to standard sequential
     * Merge Sort to prevent thread-creation overhead from ruining performance.
     */
    private static final int SEQUENTIAL_THRESHOLD = 8192;

    /**
     * Sorts the specified array of integers concurrently using Merge Sort.
     *
     * @param array the array to be sorted
     */
    public static void sort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int availableProcessors = Runtime.getRuntime().availableProcessors();

        // Calculate a safe maximum depth to prevent creating more tasks than the pool can handle.
        // This effectively prevents thread starvation deadlock in fixed-size thread pools,
        // by forcing leaf tasks to run sequentially and eventually complete.
        int maxDepth = (int) (Math.log(availableProcessors) / Math.log(2)) + 1;

        ThreadPoolExecutor executor = new ThreadPoolExecutor(availableProcessors, availableProcessors, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

        try {
            int[] tempArray = new int[array.length];
            concurrentMergeSort(array, tempArray, 0, array.length - 1, executor, maxDepth);
        } finally {
            // Ensure the executor is gracefully shut down
            executor.shutdown();
        }
    }

    /**
     * Recursively sorts the array utilizing the provided executor for concurrency.
     *
     * @param array    the array to sort
     * @param temp     a temporary array for merging
     * @param left     the starting index of the sub-array
     * @param right    the ending index of the sub-array
     * @param executor the {@link ThreadPoolExecutor} to handle concurrent tasks
     * @param depth    the remaining depth for allowing concurrent execution
     */
    private static void concurrentMergeSort(int[] array, int[] temp, int left, int right, ThreadPoolExecutor executor, int depth) {
        int length = right - left + 1;

        // Switch to sequential sort if the array is small or we have reached the maximum concurrent depth
        if (length < SEQUENTIAL_THRESHOLD || depth <= 0) {
            sequentialMergeSort(array, temp, left, right);
            return;
        }

        int mid = left + (right - left) / 2;

        // Submit the left half for concurrent execution
        CompletableFuture<Void> leftTask = CompletableFuture.runAsync(() -> concurrentMergeSort(array, temp, left, mid, executor, depth - 1), executor);

        // Process the right half in the current thread to optimize resource usage
        concurrentMergeSort(array, temp, mid + 1, right, executor, depth - 1);

        // Wait for the concurrently executed left half to complete
        leftTask.join();

        merge(array, temp, left, mid, right);
    }

    /**
     * Sorts the specified sub-array sequentially using standard Merge Sort.
     *
     * @param array the array to sort
     * @param temp  a temporary array for merging
     * @param left  the starting index of the sub-array
     * @param right the ending index of the sub-array
     */
    private static void sequentialMergeSort(int[] array, int[] temp, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        sequentialMergeSort(array, temp, left, mid);
        sequentialMergeSort(array, temp, mid + 1, right);
        merge(array, temp, left, mid, right);
    }

    /**
     * Merges two sorted sub-arrays into a single sorted sub-array.
     *
     * @param array the original array containing the sub-arrays
     * @param temp  a temporary array used for merging
     * @param left  the starting index of the first sub-array
     * @param mid   the ending index of the first sub-array (and the partition point)
     * @param right the ending index of the second sub-array
     */
    private static void merge(int[] array, int[] temp, int left, int mid, int right) {
        System.arraycopy(array, left, temp, left, right - left + 1);

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                array[k++] = temp[i++];
            } else {
                array[k++] = temp[j++];
            }
        }

        while (i <= mid) {
            array[k++] = temp[i++];
        }

        // Remaining elements from the right half are already in their correct relative positions
    }
}
