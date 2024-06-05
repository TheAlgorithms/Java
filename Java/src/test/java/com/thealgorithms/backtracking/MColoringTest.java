package com.thealgorithms.backtracking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */
class MColoringTest {

    @Test
    void testGraphColoring1() {
        int n = 4;
        int[][] graph = {{0, 1, 1, 1}, {1, 0, 1, 0}, {1, 1, 0, 1}, {1, 0, 1, 0}};
        int m = 3; // Number of colors

        assertEquals(1, MColoring.possiblePaint(createGraph(graph), n, m));
    }

    @Test
    void testGraphColoring2() {
        int n = 5;
        int[][] graph = {{0, 1, 1, 1, 0}, {1, 0, 0, 1, 0}, {1, 0, 0, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 1, 1, 0}};
        int m = 2; // Number of colors

        assertEquals(0, MColoring.possiblePaint(createGraph(graph), n, m));
    }

    @Test
    void testGraphColoring3() {
        int n = 3;
        int[][] graph = {{0, 1, 1}, {1, 0, 1}, {1, 1, 0}};
        int m = 2; // Number of colors

        assertEquals(0, MColoring.possiblePaint(createGraph(graph), n, m));
    }

    private ArrayList<Node> createGraph(int[][] graph) {
        int n = graph.length;
        ArrayList<Node> nodes = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            nodes.add(new Node());
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) { // Use j = i + 1 to avoid setting edges twice
                if (graph[i][j] > 0) {
                    nodes.get(i + 1).edges.add(j + 1);
                    nodes.get(j + 1).edges.add(i + 1);
                }
            }
        }
        return nodes;
    }
}
