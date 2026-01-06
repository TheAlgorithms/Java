package com.thealgorithms.datastructures.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Graph class in ConnectedComponent.java.
 * Tests the depth-first search implementation and connected component counting.
 * Covers various graph topologies including:
 * - Single connected components
 * - Multiple disconnected components
 * - Self-loops
 * - Linear chains
 * - Cyclic graphs
 */
class ConnectedComponentTest {

    @Test
    void testSingleConnectedComponent() {
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 1);

        assertEquals(1, graph.countGraphs());
    }

    @Test
    void testTwoDisconnectedComponents() {
        Graph<Integer> graph = new Graph<>();
        // Component 1: 1-2-3
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        // Component 2: 4-5
        graph.addEdge(4, 5);

        assertEquals(2, graph.countGraphs());
    }

    @Test
    void testThreeDisconnectedComponents() {
        Graph<Character> graph = new Graph<>();
        // Component 1: a-b-c-d-e
        graph.addEdge('a', 'b');
        graph.addEdge('a', 'e');
        graph.addEdge('b', 'e');
        graph.addEdge('b', 'c');
        graph.addEdge('c', 'd');
        graph.addEdge('d', 'a');
        // Component 2: x-y-z
        graph.addEdge('x', 'y');
        graph.addEdge('x', 'z');
        // Component 3: w (self-loop)
        graph.addEdge('w', 'w');

        assertEquals(3, graph.countGraphs());
    }

    @Test
    void testSingleNodeSelfLoop() {
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(1, 1);

        assertEquals(1, graph.countGraphs());
    }

    @Test
    void testLinearChain() {
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

        assertEquals(1, graph.countGraphs());
    }

    @Test
    void testStarTopology() {
        // Star graph with center node 0 connected to nodes 1, 2, 3, 4
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(0, 4);

        assertEquals(1, graph.countGraphs());
    }

    @Test
    void testCompleteGraph() {
        // Complete graph K4: every node connected to every other node
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);

        assertEquals(1, graph.countGraphs());
    }

    @Test
    void testStringVertices() {
        Graph<String> graph = new Graph<>();
        // Component 1
        graph.addEdge("New York", "Los Angeles");
        graph.addEdge("Los Angeles", "Chicago");
        // Component 2
        graph.addEdge("London", "Paris");
        // Component 3
        graph.addEdge("Tokyo", "Tokyo");

        assertEquals(3, graph.countGraphs());
    }

    @Test
    void testEmptyGraph() {
        Graph<Integer> graph = new Graph<>();
        assertEquals(0, graph.countGraphs());
    }

    @Test
    void testDepthFirstSearchBasic() {
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        // Get the first node and perform DFS
        assertNotNull(graph.nodeList);
        assertEquals(3, graph.nodeList.size());
    }

    @Test
    void testManyIsolatedComponents() {
        Graph<Integer> graph = new Graph<>();
        // Create 5 isolated components (each is a self-loop)
        graph.addEdge(1, 1);
        graph.addEdge(2, 2);
        graph.addEdge(3, 3);
        graph.addEdge(4, 4);
        graph.addEdge(5, 5);

        assertEquals(5, graph.countGraphs());
    }

    @Test
    void testBidirectionalEdges() {
        Graph<Integer> graph = new Graph<>();
        // Note: This is a directed graph representation
        // Adding edge 1->2 does not automatically add 2->1
        graph.addEdge(1, 2);
        graph.addEdge(2, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 2);

        assertEquals(1, graph.countGraphs());
    }

    @Test
    void testCyclicGraph() {
        Graph<Integer> graph = new Graph<>();
        // Create a cycle: 1 -> 2 -> 3 -> 4 -> 1
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 1);

        assertEquals(1, graph.countGraphs());
    }

    @Test
    void testMultipleCycles() {
        Graph<Integer> graph = new Graph<>();
        // Cycle 1: 1 -> 2 -> 3 -> 1
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        // Cycle 2: 4 -> 5 -> 4
        graph.addEdge(4, 5);
        graph.addEdge(5, 4);

        assertEquals(2, graph.countGraphs());
    }

    @Test
    void testIntegerGraphFromMainExample() {
        // Recreate the example from main method
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(7, 8);
        graph.addEdge(8, 10);
        graph.addEdge(10, 8);

        assertEquals(2, graph.countGraphs());
    }
}
