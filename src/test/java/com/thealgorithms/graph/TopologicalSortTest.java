package com.thealgorithms.graph;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TopologicalSortTest {

    @Test
    @DisplayName("Simple DAG returns correct ordering")
    void simpleDAG() {
        List<int[]> edges = Arrays.asList(
                new int[] { 0, 1 },
                new int[] { 0, 2 },
                new int[] { 1, 3 },
                new int[] { 2, 3 });
        int[] result = TopologicalSort.sort(4, edges);
        assertArrayEquals(new int[] { 0, 1, 2, 3 }, result);
    }

    @Test
    @DisplayName("Empty graph returns valid ordering")
    void emptyGraph() {
        List<int[]> edges = Arrays.asList();
        int[] result = TopologicalSort.sort(3, edges);
        assertEquals(3, result.length);
        // Any permutation is valid for empty graph
        assertTrue(Arrays.stream(result).allMatch(v -> v >= 0 && v < 3));
    }

    @Test
    @DisplayName("Graph with cycle returns null")
    void graphWithCycle() {
        List<int[]> edges = Arrays.asList(
                new int[] { 0, 1 },
                new int[] { 1, 2 },
                new int[] { 2, 0 });
        assertNull(TopologicalSort.sort(3, edges));
    }

    @Test
    @DisplayName("Course prerequisites example")
    void coursePrerequisites() {
        // Example: Course prerequisites where edge [a,b] means course a must be taken
        // before b
        List<int[]> edges = Arrays.asList(
                new int[] { 1, 0 }, // Calculus I -> Calculus II
                new int[] { 2, 0 }, // Linear Algebra -> Calculus II
                new int[] { 1, 3 }, // Calculus I -> Differential Equations
                new int[] { 2, 3 } // Linear Algebra -> Differential Equations
        );
        List<Integer> result = TopologicalSort.sortAndDetectCycle(4, edges);
        // Either [1,2,0,3] or [2,1,0,3] is valid
        assertEquals(4, result.size());
        // Verify prerequisites are satisfied
        assertTrue(result.indexOf(1) < result.indexOf(0)); // Calc I before Calc II
        assertTrue(result.indexOf(2) < result.indexOf(0)); // Linear Algebra before Calc II
        assertTrue(result.indexOf(1) < result.indexOf(3)); // Calc I before Diff Eq
        assertTrue(result.indexOf(2) < result.indexOf(3)); // Linear Algebra before Diff Eq
    }

    @Test
    @DisplayName("Invalid vertex throws exception")
    void invalidVertex() {
        List<int[]> edges = Arrays.asList(
                new int[] { 0, 5 } // Vertex 5 is invalid for a graph with 3 vertices
        );
        assertThrows(IllegalArgumentException.class, () -> TopologicalSort.sort(3, edges));
    }

    @Test
    @DisplayName("Null edge list throws exception")
    void nullEdgeList() {
        assertThrows(IllegalArgumentException.class, () -> TopologicalSort.sort(3, null));
    }

    @Test
    @DisplayName("sortAndDetectCycle throws exception for cyclic graph")
    void detectCycleThrowsException() {
        List<int[]> edges = Arrays.asList(
                new int[] { 0, 1 },
                new int[] { 1, 2 },
                new int[] { 2, 0 });
        assertThrows(IllegalArgumentException.class,
                () -> TopologicalSort.sortAndDetectCycle(3, edges));
    }
}