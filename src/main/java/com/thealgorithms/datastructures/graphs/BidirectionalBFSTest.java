package com.thealgorithms.graphs;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Arrays;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for BidirectionalBFS.
 */
public class BidirectionalBFSTest
{
    @Test
    public void testPathExists()
    {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(0, 3));
        graph.put(2, Arrays.asList(0, 3, 4));
        graph.put(3, Arrays.asList(1, 2, 5));
        graph.put(4, Arrays.asList(2, 5));
        graph.put(5, Arrays.asList(3, 4));

        assertTrue(BidirectionalBFS.bidirectionalBFS(graph, 0, 5));
    }

    @Test
    public void testPathDoesNotExist()
    {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1));
        graph.put(1, Arrays.asList(0));
        // node 2 is isolated
        graph.put(2, Arrays.asList());

        assertFalse(BidirectionalBFS.bidirectionalBFS(graph, 0, 2));
    }
}

