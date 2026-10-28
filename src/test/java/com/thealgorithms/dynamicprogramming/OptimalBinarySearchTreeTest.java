package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class OptimalBinarySearchTreeTest {

    @ParameterizedTest
    @MethodSource("validTestCases")
    void testFindOptimalCost(int[] keys, int[] frequencies, long expectedCost) {
        assertEquals(expectedCost, OptimalBinarySearchTree.findOptimalCost(keys, frequencies));
    }

    private static Stream<Arguments> validTestCases() {
        return Stream.of(Arguments.of(new int[] {}, new int[] {}, 0L), Arguments.of(new int[] {15}, new int[] {9}, 9L), Arguments.of(new int[] {10, 12}, new int[] {34, 50}, 118L), Arguments.of(new int[] {20, 10, 30}, new int[] {50, 34, 8}, 134L),
            Arguments.of(new int[] {12, 10, 20, 42, 25, 37}, new int[] {8, 34, 50, 3, 40, 30}, 324L), Arguments.of(new int[] {1, 2, 3}, new int[] {0, 0, 0}, 0L));
    }

    @ParameterizedTest
    @MethodSource("crossCheckTestCases")
    void testFindOptimalCostAgainstBruteForce(int[] keys, int[] frequencies) {
        assertEquals(bruteForceOptimalCost(keys, frequencies), OptimalBinarySearchTree.findOptimalCost(keys, frequencies));
    }

    private static Stream<Arguments> crossCheckTestCases() {
        return Stream.of(Arguments.of(new int[] {3, 1, 2}, new int[] {4, 2, 6}), Arguments.of(new int[] {5, 2, 8, 6}, new int[] {3, 7, 1, 4}), Arguments.of(new int[] {9, 4, 11, 2}, new int[] {1, 8, 2, 5}));
    }

    @ParameterizedTest
    @MethodSource("invalidTestCases")
    void testFindOptimalCostInvalidInput(int[] keys, int[] frequencies) {
        assertThrows(IllegalArgumentException.class, () -> OptimalBinarySearchTree.findOptimalCost(keys, frequencies));
    }

    private static Stream<Arguments> invalidTestCases() {
        return Stream.of(Arguments.of(null, new int[] {}), Arguments.of(new int[] {}, null), Arguments.of(new int[] {1, 2}, new int[] {3}), Arguments.of(new int[] {1, 1}, new int[] {2, 3}), Arguments.of(new int[] {1, 2}, new int[] {3, -1}));
    }

    private static long bruteForceOptimalCost(int[] keys, int[] frequencies) {
        int[][] sortedNodes = new int[keys.length][2];
        for (int index = 0; index < keys.length; index++) {
            sortedNodes[index][0] = keys[index];
            sortedNodes[index][1] = frequencies[index];
        }
        Arrays.sort(sortedNodes, java.util.Comparator.comparingInt(node -> node[0]));

        int[] sortedFrequencies = new int[sortedNodes.length];
        for (int index = 0; index < sortedNodes.length; index++) {
            sortedFrequencies[index] = sortedNodes[index][1];
        }

        return bruteForceOptimalCost(sortedFrequencies, 0, sortedFrequencies.length - 1, 1);
    }

    private static long bruteForceOptimalCost(int[] frequencies, int start, int end, int depth) {
        if (start > end) {
            return 0L;
        }

        long minimumCost = Long.MAX_VALUE;
        for (int root = start; root <= end; root++) {
            long currentCost = (long) depth * frequencies[root] + bruteForceOptimalCost(frequencies, start, root - 1, depth + 1) + bruteForceOptimalCost(frequencies, root + 1, end, depth + 1);
            minimumCost = Math.min(minimumCost, currentCost);
        }
        return minimumCost;
    }
}
