package com.thealgorithms.sorts;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class MergeSortNoExtraSpaceTest {
    record TestCase(int[] inputArray, int[] expectedArray) {
    }

    static Stream<TestCase> provideTestCases() {
        return Stream.of(new TestCase(new int[] {}, new int[] {}), new TestCase(new int[] {1}, new int[] {1}), new TestCase(new int[] {1, 2, 3, 4, 5}, new int[] {1, 2, 3, 4, 5}), new TestCase(new int[] {5, 4, 3, 2, 1}, new int[] {1, 2, 3, 4, 5}),
            new TestCase(new int[] {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5}, new int[] {1, 1, 2, 3, 3, 4, 5, 5, 5, 6, 9}), new TestCase(new int[] {4, 2, 4, 3, 2, 1, 5}, new int[] {1, 2, 2, 3, 4, 4, 5}), new TestCase(new int[] {0, 0, 0, 0}, new int[] {0, 0, 0, 0}),
            new TestCase(new int[] {1000, 500, 100, 50, 10, 5, 1}, new int[] {1, 5, 10, 50, 100, 500, 1000}), new TestCase(new int[] {1, 2, 3, 1, 2, 3, 1, 2, 3}, new int[] {1, 1, 1, 2, 2, 2, 3, 3, 3}),
            new TestCase(new int[] {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0}, new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}), new TestCase(new int[] {2, 1}, new int[] {1, 2}), new TestCase(new int[] {1, 3, 2}, new int[] {1, 2, 3}));
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    public void testCountingSort(TestCase testCase) {
        int[] outputArray = MergeSortNoExtraSpace.sort(testCase.inputArray);
        assertArrayEquals(testCase.expectedArray, outputArray);
    }

    @Test
    public void testNegativeNumbers() {
        int[] arrayWithNegatives = {1, -2, 3, -4};
        assertThrows(IllegalArgumentException.class, () -> MergeSortNoExtraSpace.sort(arrayWithNegatives));
    }
}
