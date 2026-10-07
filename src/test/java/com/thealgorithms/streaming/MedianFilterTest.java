package com.thealgorithms.streaming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MedianFilterTest {

    /**
     * Median of the last {@code windowSize} samples of {@code signal} ending at {@code index},
     * computed the obvious way.
     */
    private static double bruteForceMedian(double[] signal, int index, int windowSize) {
        int from = Math.max(0, index - windowSize + 1);
        double[] window = Arrays.copyOfRange(signal, from, index + 1);
        Arrays.sort(window);
        int middle = window.length / 2;
        return window.length % 2 != 0 ? window[middle] : 0.5 * (window[middle - 1] + window[middle]);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, Integer.MIN_VALUE})
    void rejectsNonPositiveWindowSizes(int windowSize) {
        assertThrows(IllegalArgumentException.class, () -> new MedianFilter(windowSize));
    }

    @Test
    void queriesBeforeTheFirstSampleFail() {
        MedianFilter filter = new MedianFilter(3);
        assertTrue(filter.isEmpty());
        assertFalse(filter.isFull());
        assertEquals(0, filter.size());
        assertEquals(3, filter.windowSize());
        assertEquals(0, filter.sortedWindow().length);
        assertThrows(IllegalStateException.class, filter::median);
        assertThrows(IllegalStateException.class, filter::min);
        assertThrows(IllegalStateException.class, filter::max);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    void rejectsNonFiniteSamples(double value) {
        MedianFilter filter = new MedianFilter(3);
        assertThrows(IllegalArgumentException.class, () -> filter.accept(value));
    }

    @Test
    @DisplayName("before the window fills up the median is taken over what has arrived")
    void warmsUpGracefully() {
        MedianFilter filter = new MedianFilter(5);
        assertEquals(4.0, filter.accept(4.0));
        assertEquals(3.0, filter.accept(2.0), 1e-12);
        assertEquals(4.0, filter.accept(9.0), 1e-12);
        assertEquals(3, filter.size());
        assertFalse(filter.isFull());
    }

    @Test
    void aWindowOfOneIsTheIdentity() {
        MedianFilter filter = new MedianFilter(1);
        assertEquals(5.0, filter.accept(5.0));
        assertEquals(-3.0, filter.accept(-3.0));
        assertTrue(filter.isFull());
        assertEquals(-3.0, filter.min());
        assertEquals(-3.0, filter.max());
    }

    @Test
    @DisplayName("an even window averages the two middle samples")
    void handlesEvenWindows() {
        MedianFilter filter = new MedianFilter(4);
        filter.accept(1.0);
        filter.accept(2.0);
        filter.accept(3.0);
        assertEquals(2.5, filter.accept(4.0), 1e-12);
        assertEquals(3.5, filter.accept(5.0), 1e-12);
    }

    @Test
    @DisplayName("a lone spike is removed, an edge is preserved")
    void removesSpikesButKeepsEdges() {
        MedianFilter filter = new MedianFilter(3);
        double[] signal = {1.0, 1.0, 100.0, 1.0, 1.0, 9.0, 9.0, 9.0};
        double[] filtered = filter.filter(signal);
        Assertions.assertArrayEquals(new double[] {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 9.0, 9.0}, filtered, 1e-12);
    }

    @Test
    void reportsTheExtremesOfTheWindow() {
        MedianFilter filter = new MedianFilter(3);
        filter.filter(new double[] {5.0, 1.0, 9.0});
        assertEquals(1.0, filter.min());
        assertEquals(9.0, filter.max());
        Assertions.assertArrayEquals(new double[] {1.0, 5.0, 9.0}, filter.sortedWindow(), 0.0);

        filter.accept(7.0);
        assertEquals(1.0, filter.min());
        assertEquals(9.0, filter.max());
        Assertions.assertArrayEquals(new double[] {1.0, 7.0, 9.0}, filter.sortedWindow(), 0.0);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 7, 16, 31})
    @DisplayName("agrees with a brute force median for every window size")
    void matchesBruteForce(int windowSize) {
        Random random = new Random(20240517L + windowSize);
        double[] signal = new double[3_000];
        for (int i = 0; i < signal.length; i++) {
            signal[i] = random.nextInt(50);
        }

        MedianFilter filter = new MedianFilter(windowSize);
        for (int i = 0; i < signal.length; i++) {
            assertEquals(bruteForceMedian(signal, i, windowSize), filter.accept(signal[i]), 1e-12, "at index " + i);
            assertEquals(Math.min(i + 1, windowSize), filter.size());
        }
    }

    @Test
    @DisplayName("handles duplicates, which is where a sorted mirror of the window can go wrong")
    void handlesHeavyDuplication() {
        Random random = new Random(11L);
        double[] signal = new double[2_000];
        for (int i = 0; i < signal.length; i++) {
            signal[i] = random.nextInt(3);
        }

        MedianFilter filter = new MedianFilter(5);
        for (int i = 0; i < signal.length; i++) {
            assertEquals(bruteForceMedian(signal, i, 5), filter.accept(signal[i]), 1e-12, "at index " + i);
        }
    }

    @Test
    void resetEmptiesTheWindow() {
        MedianFilter filter = new MedianFilter(3);
        filter.filter(new double[] {1.0, 2.0, 3.0});
        filter.reset();

        assertTrue(filter.isEmpty());
        assertThrows(IllegalStateException.class, filter::median);
        assertEquals(8.0, filter.accept(8.0));
    }

    @Test
    void toStringMentionsTheState() {
        MedianFilter filter = new MedianFilter(3);
        assertTrue(filter.toString().contains("size=0"), filter.toString());
        filter.accept(2.0);
        assertTrue(filter.toString().contains("median=2.0"), filter.toString());
    }

    @Test
    void reportsWhetherItHoldsSamples() {
        MedianFilter filter = new MedianFilter(2);
        assertTrue(filter.isEmpty());
        filter.accept(1.0);
        assertFalse(filter.isEmpty());
        assertFalse(filter.isFull());
        filter.accept(2.0);
        assertTrue(filter.isFull());
    }
}
