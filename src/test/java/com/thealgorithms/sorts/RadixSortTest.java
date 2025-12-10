package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RadixSortTest {
    @ParameterizedTest
    @MethodSource("provideTestCases")
    public void test(int[] inputArray, int[] expectedArray) {
        assertArrayEquals(RadixSort.sort(inputArray), expectedArray);
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(Arguments.of(new int[] {170, 45, 75, 90, 802, 24, 2, 66}, new int[] {2, 24, 45, 66, 75, 90, 170, 802}), Arguments.of(new int[] {3, 3, 3, 3}, new int[] {3, 3, 3, 3}), Arguments.of(new int[] {9, 4, 6, 8, 14, 3}, new int[] {3, 4, 6, 8, 9, 14}),
            Arguments.of(new int[] {10, 90, 49, 2, 1, 5, 23}, new int[] {1, 2, 5, 10, 23, 49, 90}), Arguments.of(new int[] {1, 3, 4, 2, 7, 8}, new int[] {1, 2, 3, 4, 7, 8}), Arguments.of(new int[] {}, new int[] {}), Arguments.of(new int[] {1}, new int[] {1}),
            Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}), Arguments.of(new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1}, new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}),
            Arguments.of(new int[] {1000000000, 999999999, 888888888, 777777777}, new int[] {777777777, 888888888, 999999999, 1000000000}), Arguments.of(new int[] {123, 9, 54321, 123456789, 0}, new int[] {0, 9, 123, 54321, 123456789}));
    }

    @Test
    public void testWithNegativeNumbers() {
        assertThrows(IllegalArgumentException.class, () -> RadixSort.sort(new int[] {3, 1, 4, 1, 5, -9}));
    }
}
