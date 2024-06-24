package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


public class WaveSortTest {
    record InputData(Integer[] array) {
    }

    @ParameterizedTest
    @MethodSource("inputStream")
    public void testWaveSortMixedPositiveNegativeZero(InputData inputData) {
        WaveSort waveSort = new WaveSort();
        assertTrue(waveSort.isWaveSorted(waveSort.sort(inputData.array)));
    }

    private static Stream<InputData> inputStream() {
        return Stream.of(new InputData(new Integer[] {7, 7, 11, 3, 4, 5, 15}), new InputData(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8}), new InputData(new Integer[] {8, 7, 6, 5, 4, 3, 2, 1}), new InputData(new Integer[] {3, 3, 3, 3}), new InputData(new Integer[] {-1, -3, -2, -4, -6, -5}),
                new InputData(new Integer[] {5, 3, 1, 2, 9, 7, 6, 8, 4, 0}), new InputData(new Integer[] {1}), new InputData(new Integer[] {2, 1}), new InputData(new Integer[] {}), new InputData(new Integer[] {0, 5, -3, 2, -1, 4, -2, 1, 3}));
    }
}
