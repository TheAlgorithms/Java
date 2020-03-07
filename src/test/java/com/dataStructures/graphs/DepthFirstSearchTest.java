package com.dataStructures.graphs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DepthFirstSearchTest {
    @Test
    void testDepthFirstSearch() {
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(4, 2);
        depthFirstSearch.addEdge(0, 1);
        depthFirstSearch.addEdge(0, 2);
        depthFirstSearch.addEdge(1, 2);
        depthFirstSearch.addEdge(2, 0);
        depthFirstSearch.addEdge(2, 3);
        depthFirstSearch.addEdge(3, 3);
        Assertions.assertEquals("2 0 1 3 ", depthFirstSearch.toString());

        DepthFirstSearch depthFirstSearch2 = new DepthFirstSearch(6, 1);
        depthFirstSearch2.addEdge(0, 2);
        depthFirstSearch2.addEdge(0,3);
        depthFirstSearch2.addEdge(0, 4);
        depthFirstSearch2.addEdge(1, 1);
        depthFirstSearch2.addEdge(1,5);
        depthFirstSearch2.addEdge(1, 3);
        depthFirstSearch2.addEdge(3, 1);
        depthFirstSearch2.addEdge(3, 3);
        depthFirstSearch2.addEdge(4, 5);
        Assertions.assertEquals("1 5 3 ", depthFirstSearch2.toString());
    }
}
