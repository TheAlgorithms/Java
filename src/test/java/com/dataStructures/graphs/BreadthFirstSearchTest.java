package com.dataStructures.graphs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BreadthFirstSearchTest {

    @Test
    void testBreadthFirstSearch() {
        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch(4, 2);
        breadthFirstSearch.addEdge(0, 1);
        breadthFirstSearch.addEdge(0, 2);
        breadthFirstSearch.addEdge(1, 2);
        breadthFirstSearch.addEdge(2, 0);
        breadthFirstSearch.addEdge(2, 3);
        breadthFirstSearch.addEdge(3, 3);
        Assertions.assertEquals("2 0 3 1 ", breadthFirstSearch.toString());

        BreadthFirstSearch breadthFirstSearch2 = new BreadthFirstSearch(6, 1);
        breadthFirstSearch2.addEdge(0, 2);
        breadthFirstSearch2.addEdge(0,3);
        breadthFirstSearch2.addEdge(0, 4);
        breadthFirstSearch2.addEdge(1, 1);
        breadthFirstSearch2.addEdge(1,5);
        breadthFirstSearch2.addEdge(1, 3);
        breadthFirstSearch2.addEdge(3, 1);
        breadthFirstSearch2.addEdge(3, 3);
        breadthFirstSearch2.addEdge(4, 5);
        Assertions.assertEquals("1 5 3 ", breadthFirstSearch2.toString());
    }
}
