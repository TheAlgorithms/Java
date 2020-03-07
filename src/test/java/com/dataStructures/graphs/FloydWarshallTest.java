package com.dataStructures.graphs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FloydWarshallTest {
    @Test
    void testFloydWarshall() {
        final int INF = 99999;
        int[][] graph = {
                {0, 5, INF, 10},
                {INF, 0, 3, INF},
                {INF, INF, 0, 1},
                {INF, INF, INF, 0}};
        FloydWarshall floydWarshall = new FloydWarshall(graph, 4);
        Assertions.assertEquals("0  5  8  9  \n" +
                "INF 0  3  4  \n" +
                "INF INF 0  1  \n" +
                "INF INF INF 0  \n", floydWarshall.toString());

        int[][] graph2 = {
                {0, 4, 2, INF, 3},
                {INF, 0, 1, INF, INF},
                {INF, INF, 0, 6, INF},
                {INF, INF, INF, 0, 5},
                {INF, INF, INF, INF, 0}};
        FloydWarshall floydWarshall2 = new FloydWarshall(graph2, 5);
        Assertions.assertEquals("0  4  2  8  3  \n" +
                "INF 0  1  7  12  \n" +
                "INF INF 0  6  11  \n" +
                "INF INF INF 0  5  \n" +
                "INF INF INF INF 0  \n", floydWarshall2.toString());

    }
}
