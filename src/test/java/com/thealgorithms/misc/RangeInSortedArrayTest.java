package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RangeInSortedArrayTest {

    @ParameterizedTest(name = "Test case {index}: {3}")
    @MethodSource("provideSortedRangeTestCases")
    void testSortedRange(int[] nums, int key, int[] expectedRange, String description) {
        assertArrayEquals(expectedRange, RangeInSortedArray.sortedRange(nums, key), description);
    }

    private static Stream<Arguments> provideSortedRangeTestCases() {
        return Stream.of(Arguments.of(new int[] {1, 2, 3, 3, 3, 4, 5}, 3, new int[] {2, 4}, "Range for key 3 with multiple occurrences"), Arguments.of(new int[] {1, 2, 3, 3, 3, 4, 5}, 4, new int[] {5, 5}, "Range for key 4 with single occurrence"),
            Arguments.of(new int[] {0, 1, 2}, 3, new int[] {-1, -1}, "Range for non-existent key"), Arguments.of(new int[] {}, 1, new int[] {-1, -1}, "Range in empty array"), Arguments.of(new int[] {1, 1, 1, 2, 3, 4, 5, 5, 5}, 1, new int[] {0, 2}, "Range for key at start"),
            Arguments.of(new int[] {1, 1, 1, 2, 3, 4, 5, 5, 5}, 5, new int[] {6, 8}, "Range for key at end"));
    }

    @ParameterizedTest(name = "Test case {index}: {3}")
    @MethodSource("provideGetCountLessThanTestCases")
    void testGetCountLessThan(int[] nums, int key, int expectedCount, String description) {
        assertEquals(expectedCount, RangeInSortedArray.getCountLessThan(nums, key), description);
    }

    private static Stream<Arguments> provideGetCountLessThanTestCases() {
        return Stream.of(Arguments.of(new int[] {1, 2, 3, 3, 4, 5}, 3, 4, "Count of elements less than existing key"), Arguments.of(new int[] {1, 2, 3, 3, 4, 5}, 4, 5, "Count of elements less than non-existing key"), Arguments.of(new int[] {1, 2, 2, 3}, 5, 4, "Count with all smaller elements"),
            Arguments.of(new int[] {2, 3, 4, 5}, 1, 0, "Count with no smaller elements"), Arguments.of(new int[] {}, 1, 0, "Count in empty array"));
    }

    @ParameterizedTest(name = "Edge case {index}: {3}")
    @MethodSource("provideEdgeCasesForSortedRange")
    void testSortedRangeEdgeCases(int[] nums, int key, int[] expectedRange, String description) {
        assertArrayEquals(expectedRange, RangeInSortedArray.sortedRange(nums, key), description);
    }

    private static Stream<Arguments> provideEdgeCasesForSortedRange() {
        return Stream.of(Arguments.of(new int[] {5, 5, 5, 5, 5}, 5, new int[] {0, 4}, "All elements same as key"), Arguments.of(new int[] {1, 2, 3, 4, 5}, 1, new int[] {0, 0}, "Key is first element"), Arguments.of(new int[] {1, 2, 3, 4, 5}, 5, new int[] {4, 4}, "Key is last element"),
            Arguments.of(new int[] {1, 2, 3, 4, 5}, 0, new int[] {-1, -1}, "Key less than all elements"), Arguments.of(new int[] {1, 2, 3, 4, 5}, 6, new int[] {-1, -1}, "Key greater than all elements"),
            Arguments.of(new int[] {1, 2, 2, 2, 3, 3, 3, 4}, 3, new int[] {4, 6}, "Multiple occurrences spread"), Arguments.of(new int[] {2}, 2, new int[] {0, 0}, "Single element array key exists"), Arguments.of(new int[] {2}, 3, new int[] {-1, -1}, "Single element array key missing"));
    }

    @ParameterizedTest(name = "Edge case {index}: {3}")
    @MethodSource("provideEdgeCasesForGetCountLessThan")
    void testGetCountLessThanEdgeCases(int[] nums, int key, int expectedCount, String description) {
        assertEquals(expectedCount, RangeInSortedArray.getCountLessThan(nums, key), description);
    }

    private static Stream<Arguments> provideEdgeCasesForGetCountLessThan() {
        return Stream.of(Arguments.of(new int[] {1, 2, 3, 4, 5}, 0, 0, "Key less than all elements"), Arguments.of(new int[] {1, 2, 3, 4, 5}, 6, 5, "Key greater than all elements"), Arguments.of(new int[] {1}, 0, 0, "Single element greater than key"));
    }
}
