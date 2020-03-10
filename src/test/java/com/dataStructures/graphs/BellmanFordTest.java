package com.dataStructures.graphs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BellmanFordTest {
    @Test
    void testBellmanFord() {
        BellmanFord.Edge[] edges = new BellmanFord.Edge[5];

        BellmanFord.Edge edge1 = new BellmanFord.Edge(0, 1, 5);
        BellmanFord.Edge edge2 = new BellmanFord.Edge(0, 2, 4);
        BellmanFord.Edge edge3 = new BellmanFord.Edge(1, 3, 3);
        BellmanFord.Edge edge4 = new BellmanFord.Edge(2, 1, -6);
        BellmanFord.Edge edge5 = new BellmanFord.Edge(3, 2, 2);

        edges[0] = edge1;
        edges[1] = edge2;
        edges[2] = edge3;
        edges[3] = edge4;
        edges[4] = edge5;

        BellmanFord.Graph graph = new BellmanFord.Graph(4, 5, edges);

        BellmanFord bellmanFord = new BellmanFord(graph, 0);
        Assertions.assertEquals("Vertex: 0 distance: 0\n" +
                "Vertex: 1 distance: -3\n" +
                "Vertex: 2 distance: 3\n" +
                "Vertex: 3 distance: 1\n" +
                "Negative weight cycle detected.", bellmanFord.toString());
    }

    @Test
    void testBellmanFord2() {
        BellmanFord.Edge[] edges = new BellmanFord.Edge[8];

        BellmanFord.Edge edge1 = new BellmanFord.Edge(0, 1, -1);
        BellmanFord.Edge edge2 = new BellmanFord.Edge(0, 2, 4);
        BellmanFord.Edge edge3 = new BellmanFord.Edge(1, 2, 3);
        BellmanFord.Edge edge4 = new BellmanFord.Edge(1, 3, 2);
        BellmanFord.Edge edge5 = new BellmanFord.Edge(1, 4, 2);
        BellmanFord.Edge edge6 = new BellmanFord.Edge(3, 2, 5);
        BellmanFord.Edge edge7 = new BellmanFord.Edge(3, 1, 1);
        BellmanFord.Edge edge8 = new BellmanFord.Edge(4, 3, -3);

        edges[0] = edge1;
        edges[1] = edge2;
        edges[2] = edge3;
        edges[3] = edge4;
        edges[4] = edge5;
        edges[5] = edge6;
        edges[6] = edge7;
        edges[7] = edge8;

        BellmanFord.Graph graph = new BellmanFord.Graph(5, 8, edges);

        BellmanFord bellmanFord = new BellmanFord(graph, 0);
        Assertions.assertEquals("Vertex: 0 distance: 0\n" +
                "Vertex: 1 distance: -1\n" +
                "Vertex: 2 distance: 2\n" +
                "Vertex: 3 distance: -2\n" +
                "Vertex: 4 distance: 1\n", bellmanFord.toString());
    }
}
