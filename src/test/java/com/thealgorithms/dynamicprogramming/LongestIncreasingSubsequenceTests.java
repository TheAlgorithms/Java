package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LongestIncreasingSubsequenceTests {
    @FunctionalInterface
    public interface IntArrayToInt {
        int apply(int[] array);
    }

    @ParameterizedTest
    @MethodSource("testCases")
    public void testLongestIncreasingSubsequence(final int expected, final int[] input, final IntArrayToInt method) {
        assertEquals(expected, method.apply(input));
    }

    private static Stream<Arguments> testCases() {
        final Object[][] testData = {
            {0, new int[] {}},
            {1, new int[] {1}},
            {1, new int[] {2, 2}},
            {1, new int[] {3, 3, 3}},
            {1, new int[] {4, 4, 4, 4}},
            {1, new int[] {5, 5, 5, 5, 5}},
            {2, new int[] {1, 2}},
            {2, new int[] {1, 2, 2, 2, 2}},
            {2, new int[] {1, 0, 2}},
            {3, new int[] {1, 10, 2, 30}},
            {3, new int[] {5, 8, 3, 7, 9, 1}},
            {6, new int[] {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15}},
            {4, new int[] {10, 9, 2, 5, 3, 7, 101, 18}},
            {4, new int[] {10, 10, 9, 9, 2, 2, 5, 5, 3, 3, 7, 7, 101, 101, 18, 18}},
            {4, new int[] {0, 1, 0, 3, 2, 3}},
            {2, new int[] {1, 1, 2, 2, 2}},
            {3, new int[] {1, 1, 2, 2, 2, 3, 3, 3, 3}},
        };

        final List<IntArrayToInt> methods = Arrays.asList(LongestIncreasingSubsequence::lis, LongestIncreasingSubsequence::findLISLen);

        return Stream.of(testData).flatMap(input -> methods.stream().map(method -> Arguments.of(input[0], input[1], method)));
    }
}
