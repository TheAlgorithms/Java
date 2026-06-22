package com.thealgorithms.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Counts the number of distinct elements in every window of size k.
 *
 * @see <a href="https://www.geeksforgeeks.org/count-distinct-elements-in-every-window-of-size-k/">Reference</a>
 */
public final class CountDistinctElementsInWindow {

    private CountDistinctElementsInWindow() {
    }

    /**
     * Returns an array where each element is the count of distinct
     * elements in the corresponding window of size k.
     *
     * @param arr the input array
     * @param k   the window size
     * @return array of distinct element counts per window
     */
    public static int[] countDistinct(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0 || k > arr.length) {
            throw new IllegalArgumentException("Invalid input");
        }

        int n = arr.length;
        int[] result = new int[n - k + 1];
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int i = 0; i < k; i++) {
            freqMap.merge(arr[i], 1, Integer::sum);
        }
        result[0] = freqMap.size();

        for (int i = k; i < n; i++) {
            freqMap.merge(arr[i], 1, Integer::sum);

            int outgoing = arr[i - k];

            Integer count = freqMap.get(outgoing);
            if (count != null) {
                if (count == 1) {
                    freqMap.remove(outgoing);
                } else {
                    freqMap.put(outgoing, count - 1);
                }
            }

            result[i - k + 1] = freqMap.size();
        }

        return result;
    }
}
