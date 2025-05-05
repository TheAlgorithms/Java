package com.thealgorithms.randomized;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;

public class KargerMinCutTest {

    @Test
    public void testSimpleGraph() {
        // Graph: 0 -- 1
        Collection<Integer> nodes = Arrays.asList(0, 1);
        List<int[]> edges = List.of(new int[] {0, 1});

        KargerMinCut.KargerOutput result = KargerMinCut.findMinCut(nodes, edges);

        assertEquals(1, result.minCut());
        assertTrue(result.first().contains(0) || result.first().contains(1));
        assertTrue(result.second().contains(0) || result.second().contains(1));
    }

    @Test
    public void testTriangleGraph() {
        // Graph: 0 -- 1 -- 2 -- 0
        Collection<Integer> nodes = Arrays.asList(0, 1, 2);
        List<int[]> edges = List.of(new int[] {0, 1}, new int[] {1, 2}, new int[] {2, 0});

        KargerMinCut.KargerOutput result = KargerMinCut.findMinCut(nodes, edges);

        assertEquals(2, result.minCut());
    }

    @Test
    public void testSquareGraph() {
        // Graph: 0 -- 1
        //        |    |
        //        3 -- 2
        Collection<Integer> nodes = Arrays.asList(0, 1, 2, 3);
        List<int[]> edges = List.of(new int[] {0, 1}, new int[] {1, 2}, new int[] {2, 3}, new int[] {3, 0});

        KargerMinCut.KargerOutput result = KargerMinCut.findMinCut(nodes, edges);

        assertEquals(2, result.minCut());
    }

    @Test
    public void testDisconnectedGraph() {
        // Graph: 0 -- 1   2 -- 3
        Collection<Integer> nodes = Arrays.asList(0, 1, 2, 3);
        List<int[]> edges = List.of(new int[] {0, 1}, new int[] {2, 3});

        KargerMinCut.KargerOutput result = KargerMinCut.findMinCut(nodes, edges);

        assertEquals(0, result.minCut());
    }

    @Test
    public void testCompleteGraph() {
        // Complete Graph: 0 -- 1 -- 2 -- 3 (all nodes connected to each other)
        Collection<Integer> nodes = Arrays.asList(0, 1, 2, 3);
        List<int[]> edges = List.of(new int[] {0, 1}, new int[] {0, 2}, new int[] {0, 3}, new int[] {1, 2}, new int[] {1, 3}, new int[] {2, 3});

        KargerMinCut.KargerOutput result = KargerMinCut.findMinCut(nodes, edges);

        assertEquals(3, result.minCut());
    }

    @Test
    public void testSingleNodeGraph() {
        // Graph: Single node with no edges
        Collection<Integer> nodes = List.of(0);
        List<int[]> edges = List.of();

        KargerMinCut.KargerOutput result = KargerMinCut.findMinCut(nodes, edges);

        assertEquals(0, result.minCut());
        assertTrue(result.first().contains(0));
        assertTrue(result.second().isEmpty());
    }

    @Test
    public void testTwoNodesNoEdge() {
        // Graph: 0   1 (no edges)
        Collection<Integer> nodes = Arrays.asList(0, 1);
        List<int[]> edges = List.of();

        KargerMinCut.KargerOutput result = KargerMinCut.findMinCut(nodes, edges);

        assertEquals(0, result.minCut());
        assertTrue(result.first().contains(0) || result.first().contains(1));
        assertTrue(result.second().contains(0) || result.second().contains(1));
    }

    @Test
    public void testComplexGraph() {
        // Nodes: 0, 1, 2, 3, 4, 5, 6, 7, 8
        // Edges: Fully connected graph with additional edges for complexity
        Collection<Integer> nodes = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);
        List<int[]> edges = List.of(new int[] {0, 1}, new int[] {0, 2}, new int[] {0, 3}, new int[] {0, 4}, new int[] {0, 5}, new int[] {1, 2}, new int[] {1, 3}, new int[] {1, 4}, new int[] {1, 5}, new int[] {1, 6}, new int[] {2, 3}, new int[] {2, 4}, new int[] {2, 5}, new int[] {2, 6},
            new int[] {2, 7}, new int[] {3, 4}, new int[] {3, 5}, new int[] {3, 6}, new int[] {3, 7}, new int[] {3, 8}, new int[] {4, 5}, new int[] {4, 6}, new int[] {4, 7}, new int[] {4, 8}, new int[] {5, 6}, new int[] {5, 7}, new int[] {5, 8}, new int[] {6, 7}, new int[] {6, 8}, new int[] {7, 8},
            new int[] {0, 6}, new int[] {1, 7}, new int[] {2, 8});

        KargerMinCut.KargerOutput result = KargerMinCut.findMinCut(nodes, edges);

        // The exact minimum cut value depends on the randomization, but it should be consistent
        // for this graph structure. For a fully connected graph, the minimum cut is typically
        // determined by the smallest number of edges connecting two partitions.
        assertTrue(result.minCut() > 0);
    }
}
