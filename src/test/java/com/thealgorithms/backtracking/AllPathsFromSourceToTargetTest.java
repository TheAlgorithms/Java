package com.thealgorithms.backtracking;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import org.junit.jupiter.api.Test;

public class AllPathsFromSourceToTargetTest {

    @Test
    void testForFirstCase() {
        int vertices = 4;
        int[][] a = {{0, 1}, {0, 2}, {0, 3}, {2, 0}, {2, 1}, {1, 3}};
        int source = 2;
        int destination = 3;
        List<List<Integer>> list2 = List.of(List.of(2, 0, 1, 3), List.of(2, 0, 3), List.of(2, 1, 3));
        List<List<Integer>> list1 = AllPathsFromSourceToTarget.allPathsFromSourceToTarget(vertices, a, source, destination);
        list2 = list1;
        assertIterableEquals(list1, list2);
    }

    @Test
    void testForSecondCase() {
        int vertices = 5;
        int[][] a = {{0, 1}, {0, 2}, {0, 3}, {2, 0}, {2, 1}, {1, 3}, {1, 4}, {3, 4}, {2, 4}};
        int source = 0;
        int destination = 4;
        List<List<Integer>> list2 = List.of(List.of(0, 1, 3, 4), List.of(0, 1, 4), List.of(0, 2, 1, 3, 4), List.of(0, 2, 1, 4), List.of(0, 2, 4), List.of(0, 3, 4));
        List<List<Integer>> list1 = AllPathsFromSourceToTarget.allPathsFromSourceToTarget(vertices, a, source, destination);
        list2 = list1;
        assertIterableEquals(list1, list2);
    }

    @Test
    void testForThirdCase() {
        int vertices = 6;
        int[][] a = {{1, 0}, {2, 3}, {0, 4}, {1, 5}, {4, 3}, {0, 2}, {0, 3}, {1, 2}, {0, 5}, {3, 4}, {2, 5}, {2, 4}};
        int source = 1;
        int destination = 5;
        List<List<Integer>> list2 = List.of(List.of(1, 0, 2, 5), List.of(1, 0, 5), List.of(1, 5), List.of(1, 2, 5));
        List<List<Integer>> list1 = AllPathsFromSourceToTarget.allPathsFromSourceToTarget(vertices, a, source, destination);
        list2 = list1;
        assertIterableEquals(list1, list2);
    }

    @Test
    void testForFourthcase() {
        int vertices = 3;
        int[][] a = {{0, 1}, {0, 2}, {1, 2}};
        int source = 0;
        int destination = 2;
        List<List<Integer>> list2 = List.of(List.of(0, 1, 2), List.of(0, 2));
        List<List<Integer>> list1 = AllPathsFromSourceToTarget.allPathsFromSourceToTarget(vertices, a, source, destination);
        list2 = list1;
        assertIterableEquals(list1, list2);
    }
}