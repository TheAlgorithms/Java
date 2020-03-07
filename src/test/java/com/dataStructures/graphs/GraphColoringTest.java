package com.dataStructures.graphs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GraphColoringTest {
    @Test
    void testGraphColoring() {
        GraphColoring graphColoring = new GraphColoring(5);
        graphColoring.addEdge(0, 1);
        graphColoring.addEdge(0, 2);
        graphColoring.addEdge(1, 2);
        graphColoring.addEdge(1, 3);
        graphColoring.addEdge(2, 3);
        graphColoring.addEdge(3, 4);
        Assertions.assertEquals("Vertex 0 --->  Color 0\n" +
                "Vertex 1 --->  Color 1\n" +
                "Vertex 2 --->  Color 2\n" +
                "Vertex 3 --->  Color 0\n" +
                "Vertex 4 --->  Color 1\n", graphColoring.toString());

        GraphColoring graphColoring2 = new GraphColoring(8);
        graphColoring2.addEdge(0, 1);
        graphColoring2.addEdge(0, 3);
        graphColoring2.addEdge(0, 7);
        graphColoring2.addEdge(3, 5);
        graphColoring2.addEdge(3, 6);
        graphColoring2.addEdge(2, 6);
        graphColoring2.addEdge(2, 7);
        graphColoring2.addEdge(4, 7);
        Assertions.assertEquals("Vertex 0 --->  Color 0\n" +
                "Vertex 1 --->  Color 1\n" +
                "Vertex 2 --->  Color 0\n" +
                "Vertex 3 --->  Color 1\n" +
                "Vertex 4 --->  Color 0\n" +
                "Vertex 5 --->  Color 0\n" +
                "Vertex 6 --->  Color 2\n" +
                "Vertex 7 --->  Color 1\n", graphColoring2.toString());
    }
}
