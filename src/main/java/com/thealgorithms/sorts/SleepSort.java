package com.thealgorithms.sorts;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Sleep Sort Algorithm Implementation
 *
 * @see <a href="https://rosettacode.org/wiki/Sorting_algorithms/Sleep_sort">Sleep Sort Algorithm</a>
 */
public final class SleepSort {

    private SleepSort() {
    }

    public static int[] sort(int[] array) {
        if (array == null || array.length == 0) {
            return array;
        }

        int[] result = new int[array.length];
        CountDownLatch latch = new CountDownLatch(array.length);
        AtomicInteger index = new AtomicInteger(0);

        for (int value : array) {
            new Thread(() -> {
                try {
                    Thread.sleep(Math.abs(value) + 1);
                    result[index.getAndIncrement()] = value;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown();
                }
            }).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return result;
    }
}