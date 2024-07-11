package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class WaveSortTest {
    @ParameterizedTest
    @MethodSource("arraysToWaveSort")
    public void waveSortTest(Integer[] array) {
        WaveSort waveSort = new WaveSort();
        final var inputHistogram = getHistogram(array);
        final var sortedArray = waveSort.sort(array);
        assertTrue(waveSort.isWaveSorted(sortedArray));
        final var sortedHistogram = getHistogram(sortedArray);
        assertEquals(inputHistogram, sortedHistogram, "Element counts do not match");
    }

    private Map<Integer, Integer> getHistogram(Integer[] array) {
        Map<Integer, Integer> histogram = new HashMap<>();
        for (final var element : array) {
            histogram.put(element, histogram.getOrDefault(element, 0) + 1);
        }
        return histogram;
    }

    private static Stream<Object[]> arraysToWaveSort() {
        return Stream.of(new Object[] {new Integer[] {7, 7, 11, 3, 4, 5, 15}}, new Object[] {new Integer[] {1, 2, 3, 4, 5, 6, 7, 8}}, new Object[] {new Integer[] {8, 7, 6, 5, 4, 3, 2, 1}}, new Object[] {new Integer[] {3, 3, 3, 3}}, new Object[] {new Integer[] {-1, -3, -2, -4, -6, -5}},
            new Object[] {new Integer[] {5, 3, 1, 2, 9, 7, 6, 8, 4, 0}}, new Object[] {new Integer[] {1}}, new Object[] {new Integer[] {2, 1}}, new Object[] {new Integer[] {1, 2}}, new Object[] {new Integer[] {}}, new Object[] {new Integer[] {0, 5, -3, 2, -1, 4, -2, 1, 3}});
    }

    @ParameterizedTest
    @MethodSource("waveSortedArrays")
    public <T extends Comparable<T>> void testIsWaveSorted(T[] array, boolean expected) {
        final WaveSort waveSort = new WaveSort();
        assertEquals(expected, waveSort.isWaveSorted(array));
    }
    public static Stream<Object[]> waveSortedArrays() {
        return Stream.of(new Object[] {new Integer[] {3, 1, 4, 2, 5}, Boolean.TRUE}, new Object[] {new Integer[] {3, 1, 4, 2}, Boolean.TRUE}, new Object[] {new Integer[] {1, 3, 2, 4, 5}, Boolean.FALSE}, new Object[] {new Integer[] {4, 3, 5, 2, 3, 1, 2}, Boolean.TRUE},
            new Object[] {new Integer[] {10, 90, 49, 2, 1, 5, 23}, Boolean.FALSE}, new Object[] {new Integer[] {}, Boolean.TRUE}, new Object[] {new Integer[] {1}, Boolean.TRUE}, new Object[] {new Integer[] {2, 1}, Boolean.TRUE}, new Object[] {new Integer[] {4, 3, 2, 5}, Boolean.FALSE},
            new Object[] {new Double[] {4.0, 3.0, 5.1, 2.1, 3.3, 1.1, 2.2}, Boolean.TRUE}, new Object[] {new Double[] {10.1, 2.0, 2.0}, Boolean.TRUE}, new Object[] {new String[] {"a", "b", "c", "d"}, Boolean.FALSE}, new Object[] {new String[] {"b", "a", "b", "a", "b"}, Boolean.TRUE});
    }
}
