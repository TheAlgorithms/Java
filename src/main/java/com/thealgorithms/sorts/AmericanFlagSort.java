package com.thealgorithms.sorts;

/**
 * American Flag Sort Algorithm Implementation
 *
 * @see <a href="https://en.wikipedia.org/wiki/American_flag_sort">American Flag Sort Algorithm</a>
 */
public class AmericanFlagSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        americanFlagSort(array, 0, array.length - 1, 0);
        return array;
    }

    private <T extends Comparable<T>> void americanFlagSort(T[] array, int start, int end, int digitIndex) {
        if (start >= end || digitIndex < 0) {
            return;
        }

        int[] count = new int[256];
        int[] offset = new int[256];

        for (int i = start; i <= end; i++) {
            int digit = getDigit(array[i], digitIndex);
            count[digit]++;
        }

        offset[0] = start;
        for (int i = 1; i < 256; i++) {
            offset[i] = offset[i - 1] + count[i - 1];
        }

        for (int bucket = 0; bucket < 256; bucket++) {
            while (count[bucket] > 0) {
                int origin = offset[bucket];
                int digit = getDigit(array[origin], digitIndex);
                SortUtils.swap(array, origin, offset[digit]);
                offset[digit]++;
                count[digit]--;
            }
        }

        for (int bucket = 0; bucket < 256; bucket++) {
            int bucketStart = (bucket == 0) ? start : offset[bucket - 1];
            int bucketEnd = offset[bucket] - 1;
            if (bucketStart < bucketEnd) {
                americanFlagSort(array, bucketStart, bucketEnd, digitIndex - 1);
            }
        }
    }

    private <T extends Comparable<T>> int getDigit(T element, int digitIndex) {
        String str = element.toString();
        if (digitIndex >= str.length()) {
            return 0;
        }
        return str.charAt(str.length() - 1 - digitIndex);
    }
}