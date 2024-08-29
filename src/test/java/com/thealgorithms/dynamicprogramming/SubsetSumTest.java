package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class SubsetSumTest {

    record TestCase(int[] arr, int sum, boolean expected) {
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testSubsetSum(TestCase testCase) {
        assertEquals(testCase.expected(), SubsetSum.subsetSum(testCase.arr(), testCase.sum()));
    }

    private static Stream<TestCase> provideTestCases() {
        return Stream.of(new TestCase(new int[] {50, 4, 10, 15, 34}, 64, true), new TestCase(new int[] {50, 4, 10, 15, 34}, 99, true), new TestCase(new int[] {50, 4, 10, 15, 34}, 5, false), new TestCase(new int[] {50, 4, 10, 15, 34}, 66, false), new TestCase(new int[] {}, 0, true),
            new TestCase(new int[] {1, 2, 3}, 6, true), new TestCase(new int[] {1, 2, 3}, 7, false), new TestCase(new int[] {3, 34, 4, 12, 5, 2}, 9, true));
    }
}
